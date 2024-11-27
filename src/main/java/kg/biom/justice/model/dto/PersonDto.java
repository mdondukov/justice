package kg.biom.justice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    private Long id;
    private String slug;
    private String name;
    private String org;
    private String descr;
    private String photo;
}
