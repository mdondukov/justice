package kg.biom.justice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NavItemDto {
    private String title;
    private String url;
    private String icon;
    private List<NavItemDto> sub;
}
