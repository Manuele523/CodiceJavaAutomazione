package Utils;

import org.apache.commons.lang3.StringUtils;

import static Utils.CommonCreateFile.createFileMongolo_DELETE;

public class PreprareCommonScript_MONGO {

    private static final String segnoUguale = "=", segnoConcatena = " = TIPO ||";

    public static Integer idRecDatiMin, idRecDatiMax;
    public static String NOME_COLLECTION, REQUEST_NAME, FIELD_NAME, scriptMongolo = StringUtils.EMPTY;

    public static final String queryFilialeIsp = "db.SUPERPRATICA.updateOne({_id: ID_MONGOLO}, {$set: {FILIALE_ISP: \"STRINGA_DA_CAMBIARE\"}});\n",
            queryDeleteMany = "db.NOME_COLLECTION.deleteMany({ \n\t$and: [\n\t\t{ 'demand.requestName': 'NOME_REQUEST_NAME' },\n\t\t{ 'FIELD_NAME': {\n\t\t\t$gte: VAL_MIN,\n\t\t\t$lte: VAL_MAX\n\t\t  }\n\t\t}\n\t]\n});";


    public static void createScriptMongolo_DELETE() {
        int min = idRecDatiMin, max = idRecDatiMin + 500000, count = 1;

        do {
            scriptMongolo = queryDeleteMany
                    .replace("NOME_COLLECTION", NOME_COLLECTION)
                    .replace("NOME_REQUEST_NAME", REQUEST_NAME)
                    .replace("FIELD_NAME", FIELD_NAME)
                    .replace("VAL_MIN", Integer.toString(min))
                    .replace("VAL_MAX", Integer.toString(max));

            createFileMongolo_DELETE(REQUEST_NAME, scriptMongolo, String.valueOf(count));

            count++;
            min = max;
            max = max + 500000;
        } while (max <= idRecDatiMax);
    }

    public static void createScriptMongoloDb(Object obj, String office) {
        String id = obj.toString().replace("{$numberLong=", StringUtils.EMPTY).replace("}", StringUtils.EMPTY);
        scriptMongolo = queryFilialeIsp.replace("ID_MONGOLO", id).replace("STRINGA_DA_CAMBIARE", office);
    }

}
