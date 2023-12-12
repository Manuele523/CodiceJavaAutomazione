package ObjectForTest.IndirizzamentoWbc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Indirizzamento {

    private String area;
    private String codFilialeAperturaPratica;
    private String uoIndirizzamento;

    // ONLY FOR BONIS
    @JsonProperty("INDUSTRY")
    private String industry;
    @JsonProperty("DIREZIONE_REGIONALE")
    private String dirRegionale;

    // ONLY FOR FACTORING
    @JsonProperty("TYPE")
    private String type;
    @JsonProperty("DIREZIONI_AREE")
    private List<String> direzioniAree;

}
