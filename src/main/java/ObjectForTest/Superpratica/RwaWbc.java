package ObjectForTest.Superpratica;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RwaWbc {

    @JsonProperty("codLifaDeliberante")
    private String codLifaDeliberante;
    @JsonProperty("totRwaGB")
    private String totRwaGB;
    @JsonProperty("codIndustryRichiedente")
    private String codIndustryRichiedente;
    @JsonProperty("codClasseCompetenzaDeliberativa")
    private String codClasseCompetenzaDeliberativa;
    @JsonProperty("codITER")
    private String codITER;

}
