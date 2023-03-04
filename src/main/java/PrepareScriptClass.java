import org.junit.Test;

import static Utils.PreprareCommonScript.*;
import static Utils.PreprareCommonScript.TypeTable.T_PAD_FIDO;
import static Utils.PreprareCommonScript.TypeTable.T_PAD_NOTE_PRATICA;

public class PrepareScriptClass {

    /*******************************************************************************************************************
    * <p>                                METODO PER CREARE LE UPDATE DELLA NOTA PER                               </p> *
    * <p>                                            T_PAD_NOTE_PRATICA                                           </p> *
    * <p>                                                                                                         </p> *
    *******************************************************************************************************************/
    @Test
    public void prepareScriptSQL_T_PAD_NOTE_PRATICA() {
        String preNotaPratica = "INSERISCI_LA_TUA_NOTA";

        prepareComuneScript(preNotaPratica, T_PAD_NOTE_PRATICA, (idNotePratica = 1));
    }

    /*******************************************************************************************************************
    * <p>                                METODO PER CREARE LE UPDATE DELLA NOTA PER                               </p> *
    * <p>                                                T_PAD_FIDO                                               </p> *
    * <p>                                                                                                         </p> *
    *******************************************************************************************************************/
    @Test
    public void prepareScriptSQL_T_PAD_FIDO() {
        String preNotaFido = "INSERISCI_LA_TUA_NOTA";

        prepareComuneScript(preNotaFido, T_PAD_FIDO, (idFido = 1));
    }

}
