package api.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KakaoDTO {
    private List<BlogDocumentDTO> documents;
    private BlogMeta meta;
}
