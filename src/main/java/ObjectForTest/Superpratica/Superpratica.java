package ObjectForTest.Superpratica;

import ObjectForTest.Superpratica.AnagraficaCliente.Anagrafica;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Superpratica {

    @JsonProperty("RACCORDO_ID")
    private RaccordoId raccordoId;

    @JsonProperty("codTipoPratica")
    private String codTipoPratica;

    @JsonProperty("FILIALE_ISP")
    private String filialeIsp;
    @JsonProperty("ANAGRAFICA")
    private Anagrafica anagrafica;

    @JsonProperty("DIREZIONE_REGIONALE")
    private String dirRegionale;

    @JsonProperty("RWA_WBC")
    private RwaWbc rwaWbc;
    @JsonProperty("COD_UO_ORIGINARIO")
    private String codUoOriginario;
    @JsonProperty("PUNTO_OPERATIVO_PRATICA")
    private String puntoOperativoPratica;

    @JsonProperty("codFilialeAperturaPratica")
    private String codFilialeAperturaPratica;

    @JsonProperty("filtri")
    private Filtri filtri;

}
