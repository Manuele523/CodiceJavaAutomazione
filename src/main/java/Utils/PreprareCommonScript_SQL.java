package Utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class PreprareCommonScript_SQL {

    public enum TypeTable {
        T_PAD_NOTE_PRATICA,
        T_PAD_FIDO,
        T_PAD_FIDO_ESTESO
    }

    private static final String segnoUguale = "=", segnoConcatena = " = TIPO ||";
    private static String campo = StringUtils.EMPTY,
            query = StringUtils.EMPTY,
            DEFINE_OFF = "SET DEFINE OFF;\n";

    public static Integer idNotePratica, idFido, idFidoEsteso;
    public static String NOME_TABELLA,
            NOME_COLONNA,
            ID_NOME_TABELLA_WHERE,
            scriptSQL = StringUtils.EMPTY;

    public static Map<Integer, String> mapKV = new HashMap<>();

    public static final String queryNotePratica = "UPDATE LMBE_OWN.T_PAD_NOTE_PRATICA SET DES_NOTA QUI_IL_TIPO 'STRINGA_DA_CAMBIARE', TMS_UPDATE = SYSTIMESTAMP WHERE ID_NOTA = QUI_METTI_ID;\n",
            queryFido = "UPDATE LMBE_OWN.T_PAD_FIDO SET DES_NOTE_FIDO QUI_IL_TIPO 'STRINGA_DA_CAMBIARE', TMS_UPDATE = SYSTIMESTAMP WHERE ID_FIDO = QUI_METTI_ID;\n",
            queryFidoEsteso = "UPDATE LMBE_OWN.T_PAD_FIDO_ESTESO SET DES_NOTE_FIDO QUI_IL_TIPO 'STRINGA_DA_CAMBIARE', TMS_UPDATE = SYSTIMESTAMP WHERE ID_FIDO_ESTESO = QUI_METTI_ID;\n",
            queryDinamica = "UPDATE LMBE_OWN.NOME_TABELLA SET NOME_COLONNA QUI_IL_TIPO 'STRINGA_DA_CAMBIARE', TMS_UPDATE = SYSTIMESTAMP WHERE ID_NOME_TABELLA_WHERE = QUI_METTI_ID;\n";

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
            case T_PAD_FIDO_ESTESO:
                query = queryFidoEsteso;
                campo = "DES_NOTE_FIDO";
                break;
            default:
                break;
        }
    }

    public static void prepareComuneScriptDinamico(String nota, Integer id) {
        AtomicReference<Boolean> isFirst = new AtomicReference<>(true);
        String[] results = nota.split("(?<=\\G.{850})");
        final String[] script = {DEFINE_OFF};

        Arrays.stream(results).forEach(r -> {
            script[0] += (queryDinamica
                    .replace("NOME_TABELLA", NOME_TABELLA)
                    .replace("NOME_COLONNA", NOME_COLONNA)
                    .replace("QUI_IL_TIPO", isFirst.get() ? segnoUguale : segnoConcatena.replace("TIPO", NOME_COLONNA))
                    .replace("STRINGA_DA_CAMBIARE", r.replaceAll("'", "''"))
                    .replace("ID_NOME_TABELLA_WHERE", ID_NOME_TABELLA_WHERE)
                    .replace("QUI_METTI_ID", id.toString()));

            isFirst.set(false);
        });

        if (scriptSQL.contains(DEFINE_OFF)) {
            script[0] = script[0].replace(DEFINE_OFF, StringUtils.EMPTY);
        }
        scriptSQL += script[0] + "\n";
    }

}
