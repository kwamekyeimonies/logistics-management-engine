package logistics_management_engine.service.aws_service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import logistics_management_engine.common.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AWSService implements IAWSService{
    private final AmazonS3 amazonS3;

    @Override
    public String uploadFileToBucket(String bucket_name, String key_name, Long content_length, String content_type, InputStream value) throws AmazonClientException {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(content_length);
            metadata.setContentType(content_type);

            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket_name,key_name,value,metadata);
            amazonS3.putObject(putObjectRequest);
            URL fileUrl = amazonS3.getUrl(bucket_name, key_name);

            return fileUrl.toString();
        } catch (AmazonClientException exception) {
            throw new AmazonClientException(String.format(Messages.ERROR_FILE_UPLOAD, exception.getMessage()), exception);
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public ByteArrayInputStream retrieveFileFromBucket(String bucket_name, String key_name) throws AmazonClientException {
        try {
            S3Object s3Object = amazonS3.getObject(new GetObjectRequest(bucket_name, key_name));
            S3ObjectInputStream objectContent = s3Object.getObjectContent();
            return new ByteArrayInputStream(objectContent.readAllBytes());
        } catch (AmazonServiceException e) {
            throw new AmazonClientException(String.format(Messages.ERROR_FILE_RETRIEVE, e.getMessage()), e);
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public String deleteFileFromBucket(String bucket_name, String key_name) throws AmazonClientException {
        try {
            amazonS3.deleteObject(new DeleteObjectRequest(bucket_name, key_name));
            return String.format(Messages.FILE_DELETE_SUCCESS, bucket_name, key_name);
        } catch (AmazonClientException e) {
            throw new AmazonClientException(String.format(Messages.ERROR_FILE_DELETE, e.getMessage()), e);
        }
    }

    @Override
    public List<String> fetchAllFiles(String bucket_name) throws AmazonClientException {
        List<String> fileNames = new ArrayList<>();
        try {
            ObjectListing objectListing = amazonS3.listObjects(bucket_name);
            for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
                fileNames.add(objectSummary.getKey());
            }
            return fileNames;
        } catch (AmazonClientException e) {
            throw new AmazonClientException(String.format(Messages.ERROR_FETCH_FILES, e.getMessage()), e);
        }
    }
}
