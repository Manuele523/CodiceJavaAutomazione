import org.junit.Test;

import static Utils.PreprareCommonScript_MONGO.*;

public class PrepareScript_LMRD0 {

    /*******************************************************************************************************************
    * <p> METODO PER CREARE UNA LISTA DI DELETE                                                                   </p> *
    *                                                                                                                  *
    * <p> INSERISCI_REQUEST_NAME        -> Inserisci il requestName (es. ONE_EleggibilitaOnline)                  </p> *
    * <p> idRecDatiMin                  -> Inserisci id minore da cancellare                                      </p> *
    * <p> idRecDatiMax                  -> Inserisci id massimo da cancellare                                     </p> *
    * <p> INSERISCI_CAMPO               -> Inserisci campo da where                                               </p> *
    * <p> INSERISCI_NOME_COLLECTION     -> Inserisci nome della Collection                                        </p> *
    *                                                                                                                  *
    * <p> Finita la generazione saranno creati N° file.js all'interno del progetto. (Sotto al pom)                </p> *
    *                                                                                                                  *
    *******************************************************************************************************************/
    @Test
    public void prepareScriptMongo_DELETE_RecuperoDati() {
        NOME_COLLECTION = "RecuperoDati";
        REQUEST_NAME = "ONE_EleggibilitaOnline";
        FIELD_NAME = "idRecuperoDati";
        idRecDatiMin = 12000000;
        idRecDatiMax = 25000000;

        createScriptMongolo_DELETE();
    }

}
