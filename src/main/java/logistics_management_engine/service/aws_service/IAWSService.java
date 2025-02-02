package logistics_management_engine.service.aws_service;

import com.amazonaws.AmazonClientException;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;


public interface IAWSService {
    String uploadFileToBucket(final String bucket_name, final String key_name, final Long content_length, final String content_type, final InputStream value) throws AmazonClientException;
    ByteArrayInputStream retrieveFileFromBucket(final String bucket_name, final String key_name) throws AmazonClientException;
    String deleteFileFromBucket(final String bucket_name, final String key_name) throws AmazonClientException;
    List<String> fetchAllFiles(final String bucket_name) throws AmazonClientException;
}
