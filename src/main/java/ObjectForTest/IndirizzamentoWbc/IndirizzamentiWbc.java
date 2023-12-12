package ObjectForTest.IndirizzamentoWbc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndirizzamentiWbc {

    private ObjectId id;
    private String tipoIndirizzamento;
    private List<Indirizzamento> listaIndirizzamenti;

    private String areaActive;

}
