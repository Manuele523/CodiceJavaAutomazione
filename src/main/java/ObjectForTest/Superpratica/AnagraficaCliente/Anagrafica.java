package ObjectForTest.Superpratica.AnagraficaCliente;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Anagrafica {

    @JsonProperty("ANAG_CLIENTE")
    private AnagEntita anagCliente;

}
