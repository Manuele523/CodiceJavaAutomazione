import org.junit.Test;

import static Utils.PreprareCommonScript.*;
import static Utils.PreprareCommonScript.TypeTable.*;

public class PrepareDinamicScriptClass {

    // WIP
    /*******************************************************************************************************************
    * <p> METODO PER CREARE LE UPDATE DELLA NOTA PER TABELLA DINAMICA                                             </p> *
    *                                                                                                                  *
    * <p> numIncident              -> Inserisci il numero dell'incident                                           </p> *
    * <p> NOME_TABELLA             -> Inserisci nome  della tabella su cui fare update                            </p> *
    * <p> NOME_COLONNA             -> Inserisci nome della colonna su cui devi effettuare la bonifica             </p> *
    * <p> ID_NOME_TABELLA_WHERE    -> Inserisci nome della colonna PK su qui fare la where condition              </p> *
    * <p> mapKV                    -> Inserisci le tue note                                                       </p> *
    *                                                                                                                  *
    * <p> Finita la generazione sarà creato un file.sql all'interno del progetto. (Sotto al pom)                  </p> *
    *                                                                                                                  *
    *******************************************************************************************************************/
    @Test
    public void prepareScriptSQL_Dynamic() {
        String numIncident = "INSERISCI_NUMERO_INCIDENT";
        NOME_TABELLA = "INSERISCI_NOTE_TABELLA";
        NOME_COLONNA = "INSERISCE_NOME_COLONNA";
        ID_NOME_TABELLA_WHERE = "INSERISCI_ID_DELLA_WHERE";

        mapKV.put((idFidoEsteso = 1), "INSERISCI_LA_TUA_NOTA1");
//        mapKV.put((idFidoEsteso = 2), "INSERISCI_LA_TUA_NOTA2");

        mapKV.forEach((idNota, nota) -> {
            prepareComuneScriptDinamico(nota, idNota);
        });

        createFileSql(String.valueOf(NOME_TABELLA), numIncident);
    }

}
