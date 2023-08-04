package ObjectForTest.Superpratica;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaccordoId {

    @JsonProperty("ID_PRATICA_CONCESSIONE")
    private String idPraticaConcessione;
    @JsonProperty("NUMERO_PEF")
    private String numeroPef;

}
