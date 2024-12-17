package Utils;

import java.io.FileWriter;
import java.io.IOException;

public class CommonCreateFile {

    public static void createFileSql(String tableName, String numIncident, String scriptSQL) {
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

    public static void createFileMongolo(String numIncident, String scriptMongolo) {
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

    public static void createFileMongolo_DELETE(String numIncident, String scriptMongolo, String numScript) {
        String fileName = "V01_".concat(numScript).concat("_LMRD_CHG_DELETE_").concat(numIncident);

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
