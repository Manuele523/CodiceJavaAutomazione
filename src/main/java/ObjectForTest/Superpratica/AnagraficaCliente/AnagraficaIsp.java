package ObjectForTest.Superpratica.AnagraficaCliente;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnagraficaIsp {

    @JsonProperty("codAbi")
    private String codAbi;
    @JsonProperty("codNsg")
    private String codNsg;
    @JsonProperty("codSndg")
    private String codSndg;
    @JsonProperty("codTipoSegmentazione")
    private String codTipoSegmentazione;

}
