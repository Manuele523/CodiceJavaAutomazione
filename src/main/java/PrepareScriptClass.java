import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static Utils.PreprareCommonScript.*;
import static Utils.PreprareCommonScript.TypeTable.T_PAD_FIDO;
import static Utils.PreprareCommonScript.TypeTable.T_PAD_NOTE_PRATICA;

public class PrepareScriptClass {

    /*******************************************************************************************************************
    * <p> METODO PER CREARE LE UPDATE DELLA NOTA PER - T_PAD_NOTE_PRATICA                                         </p> *
    *                                                                                                                  *
    * <p> mapKV          -> Inserisci le tue note                                                                 </p> *
    * <p> idNotePratica  -> Inserisci l'id della nota da aggiornare                                               </p> *
    *                                                                                                                  *
    * <p> Finita la generazione sarà creato un file.sql all'interno del progetto. (Sotto al pom)                  </p> *
    *                                                                                                                  *
    *******************************************************************************************************************/
    @Test
    public void prepareScriptSQL_T_PAD_NOTE_PRATICA() {
        mapKV.put((idNotePratica = 1), "INSERISCI_LA_TUA_NOTA1");
        // mapKV.put((idNotePratica = 2), "INSERISCI_LA_TUA_NOTA2");

        mapKV.forEach((idNota, nota) -> {
            prepareComuneScript(nota, T_PAD_NOTE_PRATICA, idNota);
        });

        createFileSql(String.valueOf(T_PAD_NOTE_PRATICA));
    }

    /*******************************************************************************************************************
    * <p> METODO PER CREARE LE UPDATE DELLA NOTA PER - T_PAD_FIDO                                                 </p> *
    *                                                                                                                  *
    * <p> mapKV       -> Inserisci le tue note                                                                    </p> *
    * <p> idFido      -> Inserisci l'id della nota da aggiornare                                                  </p> *
    *                                                                                                                  *
    * <p> Finita la generazione sarà creato un file.sql all'interno del progetto. (Sotto al pom)                  </p> *
    *                                                                                                                  *
    *******************************************************************************************************************/
    @Test
    public void prepareScriptSQL_T_PAD_FIDO() {
        mapKV.put((idNotePratica = 1), "INSERISCI_LA_TUA_NOTA1");
        mapKV.put((idNotePratica = 2), "INSERISCI_LA_TUA_NOTA2");

        mapKV.forEach((idNota, nota) -> {
            prepareComuneScript(nota, T_PAD_FIDO, idNota);
        });

        createFileSql(String.valueOf(T_PAD_FIDO));
    }

}
