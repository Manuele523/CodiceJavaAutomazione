package Utils;

import org.apache.commons.lang3.StringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class PreprareCommonScript {

    public enum TypeTable {
        T_PAD_NOTE_PRATICA,
        T_PAD_FIDO,
        T_PAD_FIDO_ESTESO
    }

    private static final String segnoUguale = "=", segnoConcatena = " = TIPO ||";
    private static String campo = StringUtils.EMPTY,
            query = StringUtils.EMPTY,
            DEFINE_OFF = "SET DEFINE OFF;\n",
            scriptSQL = StringUtils.EMPTY,
            scriptMongolo = StringUtils.EMPTY;

    public static Integer idNotePratica, idFido, idFidoEsteso;

    public static Map<Integer, String> mapKV = new HashMap<>();

    public static final String queryNotePratica = "UPDATE LMBE_OWN.T_PAD_NOTE_PRATICA SET DES_NOTA QUI_IL_TIPO 'STRINGA_DA_CAMBIARE' WHERE ID_NOTA = QUI_METTI_ID;\n",
            queryFido = "UPDATE LMBE_OWN.T_PAD_FIDO SET DES_NOTE_FIDO QUI_IL_TIPO 'STRINGA_DA_CAMBIARE', TMS_UPDATE = SYSTIMESTAMP WHERE ID_FIDO = QUI_METTI_ID;\n",
            queryFidoEsteso = "UPDATE LMBE_OWN.T_PAD_FIDO_ESTESO SET DES_NOTE_FIDO QUI_IL_TIPO 'STRINGA_DA_CAMBIARE', TMS_UPDATE = SYSTIMESTAMP WHERE ID_FIDO_ESTESO = QUI_METTI_ID;\n",
            queryFilialeIsp = "db.SUPERPRATICA.updateOne({_id: ID_MONGOLO}, {$set: {FILIALE_ISP: \"STRINGA_DA_CAMBIARE\"}});\n";

    public static void prepareComuneScript(String nota, TypeTable tableName, Integer id) {
        AtomicReference<Boolean> isFirst = new AtomicReference<>(true);
        String[] results = nota.split("(?<=\\G.{850})");
        final String[] script = {DEFINE_OFF};

        getColumnName(tableName);

        Arrays.stream(results).forEach(r -> {
            script[0] += (query
                    .replace("QUI_IL_TIPO", isFirst.get() ? segnoUguale : segnoConcatena.replace("TIPO", campo))
                    .replace("STRINGA_DA_CAMBIARE", r.replaceAll("'", "''"))
                    .replace("QUI_METTI_ID", id.toString()));


            isFirst.set(false);
        });

        if (scriptSQL.contains(DEFINE_OFF)) {
            script[0] = script[0].replace(DEFINE_OFF, StringUtils.EMPTY);
        }
        scriptSQL += script[0] + "\n";
    }

    public static void createScriptMongoloDb(Object obj, String office) {
        String id = obj.toString().replace("{$numberLong=", StringUtils.EMPTY).replace("}", StringUtils.EMPTY);
        scriptMongolo = queryFilialeIsp.replace("ID_MONGOLO", id).replace("STRINGA_DA_CAMBIARE", office);
    }

    private static void getColumnName(TypeTable tableName) {
        switch (tableName) {
            case T_PAD_NOTE_PRATICA:
                query = queryNotePratica;
                campo = "DES_NOTA";
                break;
            case T_PAD_FIDO:
                query = queryFido;
                campo = "DES_NOTE_FIDO";
                break;
            case T_PAD_FIDO_ESTESO:
                query = queryFidoEsteso;
                campo = "DES_NOTE_FIDO";
                break;
            default:
                break;
        }
    }

    public static void createFileSql(String tableName, String numIncident) {
        scriptSQL += "COMMIT;";
        String fileName = "V01_01_LMBE_APP_".concat(numIncident).concat("_").concat(tableName).concat("_").concat("BONIFICA_NOTE");

        try {
            FileWriter fileout = new FileWriter(fileName.concat(".sql"));
            fileout.write(scriptSQL);
            fileout.close();

            System.out.println("Script created correctly!");
        } catch (IOException e) {
            System.out.println("An error occurred." + e);
        }
    }

    public static void createFileMongolo(String numIncident) {
        String fileName = "V01_01_LMSP_OWN_".concat(numIncident).concat("_").concat("BONIFICA_FILIALE_ISP");

        try {
            FileWriter fileout = new FileWriter(fileName.concat(".js"));
            fileout.write(scriptMongolo);
            fileout.close();

            System.out.println("\nScript created correctly!");
        } catch (IOException e) {
            System.out.println("An error occurred." + e);
        }
    }
}
