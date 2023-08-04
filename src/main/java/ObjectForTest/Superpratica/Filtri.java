package ObjectForTest.Superpratica;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filtri {

    @JsonProperty("ultimoStato")
    private String ultimoStato;
    @JsonProperty("equalPointOC")
    private Boolean equalPointOC;
    @JsonProperty("isNotAnomaloPA")
    private Boolean isNotAnomaloPA;
    @JsonProperty("codAnalista")
    private String codAnalista;
    @JsonProperty("flgClaim")
    private Boolean flgClaim;

}
