package kg.biom.justice.model;

import kg.biom.justice.model.dto.AttachFile;
import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class ContentUtil {

    public static String toDtString(Instant createDate, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.of("Asia/Bishkek"));
        return formatter.format(createDate);
    }

    public static AttachFile handleAttachFile(AttachFile file, String basePath) {
        file.setPath(mergePath(basePath, file.getPath()));
        return file;
    }

    public static String mergePath(String basePath, String relativePath) {
        return String.format("%s/%s", basePath, relativePath);
    }
}
