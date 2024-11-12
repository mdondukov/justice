package kg.biom.justice.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AttachFileType {
    PDF("file-pdf"),
    WORD("file-word"),
    OTHER("file-text");

    private final String icon;
}
