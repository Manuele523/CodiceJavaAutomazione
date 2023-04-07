import org.junit.Test;

import static Utils.PreprareCommonScript.*;
import static Utils.PreprareCommonScript.TypeTable.T_PAD_FIDO;
import static Utils.PreprareCommonScript.TypeTable.T_PAD_NOTE_PRATICA;

public class PrepareScriptClass {

    /*******************************************************************************************************************
    * <p> METODO PER CREARE LE UPDATE DELLA NOTA PER - T_PAD_NOTE_PRATICA                                         </p> *
    *                                                                                                                  *
    * <p> preNotaPratica -> Inserisci la tua nota                                                                 </p> *
    * <p> idNotePratica  -> Inserisci l'id della nota da aggiornare                                               </p> *
    *                                                                                                                  *
    * <p> Finita la generazione sarà creato un file.sql all'interno del progetto. (Sotto al pom)                  </p> *
    *                                                                                                                  *
    *******************************************************************************************************************/
    @Test
    public void prepareScriptSQL_T_PAD_NOTE_PRATICA() {
        String preNotaPratica = "INSERISCI_LA_TUA_NOTA";

        prepareComuneScript(preNotaPratica, T_PAD_NOTE_PRATICA, (idNotePratica = 1));
    }

    /*******************************************************************************************************************
    * <p> METODO PER CREARE LE UPDATE DELLA NOTA PER - T_PAD_FIDO                                                 </p> *
    *                                                                                                                  *
    * <p> preNotaFido -> Inserisci la tua nota                                                                    </p> *
    * <p> idFido      -> Inserisci l'id della nota da aggiornare                                                  </p> *
    *                                                                                                                  *
    * <p> Finita la generazione sarà creato un file.sql all'interno del progetto. (Sotto al pom)                  </p> *
    *                                                                                                                  *
    *******************************************************************************************************************/
    @Test
    public void prepareScriptSQL_T_PAD_FIDO() {
        String preNotaFido = "INSERISCI_LA_TUA_NOTA";

        prepareComuneScript(preNotaFido, T_PAD_FIDO, (idFido = 1));
    }

}
