import org.junit.Test;

import static Utils.PreprareCommonScript.*;
import static Utils.PreprareCommonScript.TypeTable.*;

public class PrepareScriptClass {

    /*******************************************************************************************************************
    * <p> METODO PER CREARE LE UPDATE DELLA NOTA PER - T_PAD_NOTE_PRATICA                                         </p> *
    *                                                                                                                  *
    * <p> numIncident    -> Inserisci il numero dell'incident                                                     </p> *
    * <p> mapKV          -> Inserisci le tue note                                                                 </p> *
    * <p> idNotePratica  -> Inserisci l'id della nota da aggiornare                                               </p> *
    *                                                                                                                  *
    * <p> Finita la generazione sarà creato un file.sql all'interno del progetto. (Sotto al pom)                  </p> *
    *                                                                                                                  *
    *******************************************************************************************************************/
    @Test
    public void prepareScriptSQL_T_PAD_NOTE_PRATICA() {
        String numIncident = "INSERISCI_NUMERO_INCIDENT";
        mapKV.put((idNotePratica = 1), "INSERISCI_LA_TUA_NOTA1");
//        mapKV.put((idNotePratica = 2), "INSERISCI_LA_TUA_NOTA2");

        mapKV.forEach((idNota, nota) -> {
            prepareComuneScript(nota, T_PAD_NOTE_PRATICA, idNota);
        });

        createFileSql(String.valueOf(T_PAD_NOTE_PRATICA), numIncident);
    }

    /*******************************************************************************************************************
    * <p> METODO PER CREARE LE UPDATE DELLA NOTA PER - T_PAD_FIDO                                                 </p> *
    *                                                                                                                  *
    * <p> numIncident    -> Inserisci il numero dell'incident                                                     </p> *
    * <p> mapKV          -> Inserisci le tue note                                                                 </p> *
    * <p> idFido         -> Inserisci l'id della nota da aggiornare                                               </p> *
    *                                                                                                                  *
    * <p> Finita la generazione sarà creato un file.sql all'interno del progetto. (Sotto al pom)                  </p> *
    *                                                                                                                  *
    *******************************************************************************************************************/
    @Test
    public void prepareScriptSQL_T_PAD_FIDO() {
        String numIncident = "INSERISCI_NUMERO_INCIDENT";
        mapKV.put((idFido = 1), "INSERISCI_LA_TUA_NOTA1");
//        mapKV.put((idFido = 2), "INSERISCI_LA_TUA_NOTA2");

        mapKV.forEach((idNota, nota) -> {
            prepareComuneScript(nota, T_PAD_FIDO, idNota);
        });

        createFileSql(String.valueOf(T_PAD_FIDO), numIncident);
    }

    /*******************************************************************************************************************
    * <p> METODO PER CREARE LE UPDATE DELLA NOTA PER - T_PAD_FIDO_ESTESO                                          </p> *
    *                                                                                                                  *
    * <p> numIncident    -> Inserisci il numero dell'incident                                                     </p> *
    * <p> mapKV          -> Inserisci le tue note                                                                 </p> *
    * <p> idFidoEsteso   -> Inserisci l'id della nota da aggiornare                                               </p> *
    *                                                                                                                  *
    * <p> Finita la generazione sarà creato un file.sql all'interno del progetto. (Sotto al pom)                  </p> *
    *                                                                                                                  *
     *******************************************************************************************************************/
    @Test
    public void prepareScriptSQL_T_PAD_FIDO_ESTESO() {
        String numIncident = "INSERISCI_NUMERO_INCIDENT";
        mapKV.put((idFidoEsteso = 1), "INSERISCI_LA_TUA_NOTA1");
//        mapKV.put((idFidoEsteso = 2), "INSERISCI_LA_TUA_NOTA2");

        mapKV.forEach((idNota, nota) -> {
            prepareComuneScript(nota, T_PAD_FIDO_ESTESO, idNota);
        });

        createFileSql(String.valueOf(T_PAD_FIDO_ESTESO), numIncident);
    }

    // WIP
    @Test
    public void prepareScriptSQL_Dynamic() {
        String numIncident = "INSERISCI_NUMERO_INCIDENT";
        NOME_TABELLA = "INSERISCI_NOTE_TABELLA";
        NOME_COLONNA = "INSERISCE_NOME_COLONNA";
        ID_DINAMICO = "INSERISCI_ID_DELLA_WHERE";

        mapKV.put((idFidoEsteso = 1), "INSERISCI_LA_TUA_NOTA1");
//        mapKV.put((idFidoEsteso = 2), "INSERISCI_LA_TUA_NOTA2");

        mapKV.forEach((idNota, nota) -> {
            prepareComuneScriptDinamico(nota, idNota);
        });

        createFileSql(String.valueOf(NOME_TABELLA), numIncident);
    }

}
