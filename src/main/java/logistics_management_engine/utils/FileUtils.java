package logistics_management_engine.utils;

import java.util.List;

public class FileUtils {

    public static final long MAX_FILE_SIZE = 5 * 1024 * 1024;  // 5MB

    private static final List<String> ALLOWED_IMAGE_TYPES = List.of(
            "image/jpeg",
            "image/png",
            "image/gif",
            "image/bmp"
    );

    public static String get_file_extension(String content_type) {
        switch (content_type.toLowerCase()) {
            case "image/jpeg":
                return ".jpg";
            case "image/png":
                return ".png";
            case "image/gif":
                return ".gif";
            case "image/bmp":
                return ".bmp";
            default:
                return ".jpg";
        }
    }

    public static boolean isImageFile(String contentType) {
        return ALLOWED_IMAGE_TYPES.contains(contentType.toLowerCase());
    }

    public static boolean isFileSizeValid(long fileSize) {
        return fileSize <= MAX_FILE_SIZE;
    }
}
