package Utils;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class PreprareCommonScript {

    public enum TypeTable {
        T_PAD_NOTE_PRATICA,
        T_PAD_FIDO
    }

    private static final String segnoUguale = "=", segnoConcatena = " = TIPO ||";
    private static String campo = StringUtils.EMPTY, query = StringUtils.EMPTY;

    public static Integer idNotePratica, idFido;

    public static final String queryNotePratica = "UPDATE LMBE_OWN.T_PAD_NOTE_PRATICA SET DES_NOTA QUI_IL_TIPO 'STRINGA_DA_CAMBIARE' WHERE ID_NOTA = QUI_METTI_ID; ",
            queryFido = "UPDATE LMBE_OWN.T_PAD_FIDO SET DES_NOTE_FIDO QUI_IL_TIPO 'STRINGA_DA_CAMBIARE', TMS_UPDATE = SYSTIMESTAMP WHERE ID_FIDO = QUI_METTI_ID; ";

    public static void prepareComuneScript(String nota, TypeTable tableName, Integer id) {
        AtomicReference<Boolean> isFirst = new AtomicReference<>(true);
        String[] results = nota.split("(?<=\\G.{850})");

        getColumnName(tableName);

        System.out.println("SET DEFINE OFF;");
        Arrays.stream(results).forEach(r -> {
            System.out.println(query
                    .replace("QUI_IL_TIPO", isFirst.get() ? segnoUguale : segnoConcatena.replace("TIPO", campo))
                    .replace("STRINGA_DA_CAMBIARE", r.replaceAll("'", "''"))
                    .replace("QUI_METTI_ID", id.toString()));

            isFirst.set(false);
        });
        System.out.println("COMMIT;");
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

}
