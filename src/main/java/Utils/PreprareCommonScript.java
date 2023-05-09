package Utils;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class PreprareCommonScript {

    public enum TypeTable {
        T_PAD_NOTE_PRATICA,
        T_PAD_FIDO
    }

    private static final String segnoUguale = "=", segnoConcatena = " = TIPO ||";
    private static String campo = StringUtils.EMPTY, query = StringUtils.EMPTY, DEFINE_OFF = "SET DEFINE OFF;\n";

    public static String scriptSQL = StringUtils.EMPTY;

    public static Integer idNotePratica, idFido;

    public static Map<Integer, String> mapKV = new HashMap<>();

    public static final String queryNotePratica = "UPDATE LMBE_OWN.T_PAD_NOTE_PRATICA SET DES_NOTA QUI_IL_TIPO 'STRINGA_DA_CAMBIARE' WHERE ID_NOTA = QUI_METTI_ID;\n",
            queryFido = "UPDATE LMBE_OWN.T_PAD_FIDO SET DES_NOTE_FIDO QUI_IL_TIPO 'STRINGA_DA_CAMBIARE', TMS_UPDATE = SYSTIMESTAMP WHERE ID_FIDO = QUI_METTI_ID;\n";

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

}
