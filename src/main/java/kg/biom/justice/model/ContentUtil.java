package kg.biom.justice.model;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ContentUtil {
    private ContentUtil() {}

    public static String toDtString(Instant createDate, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.of("Asia/Bishkek"));
        return formatter.format(createDate);
    }
}
