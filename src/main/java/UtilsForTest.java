import Utils.BuildCommon;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static Utils.BuildCommon.ERROR_CHARACTER;

public class UtilsForTest {

    private static final String notFormatted = "INSERISCI_NOTA";

    /*******************************************************************************************************************
    * <p> FIND SPECIAL CHARACTERS INTO CREDIT_MEMO.XML                                                            </p> *
    *                                                                                                                  *
    * <p> dirtyCM.xml    -> Insert into /resources/dirtyCM.xml your creditMemo.xml broken                         </p> *
    *                                                                                                                  *
    * <p> REMEMBER: Always check if you can print the new CM!!                                                    </p> *
    *                                                                                                                  *
    *******************************************************************************************************************/
    @Test
    public void findSpecialCharacters() {
        List<Integer> errors = new ArrayList<>();
        try {
            List<String> docLines = Files.readAllLines(Paths.get("src/main/resources/dirtyCM.xml"), StandardCharsets.UTF_8);
            for (int i = 0; i < docLines.size(); i++) {
                String line = docLines.get(i);
                if (line.contains(ERROR_CHARACTER)) {
                    errors.add(i+1);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(errors.size() + " Invalid XML characters at row: " + errors);
    }

    /*******************************************************************************************************************
     * <p> TRANSFORM DB_NOTES INTO CM_NOTES                                                                        </p> *
     *                                                                                                                  *
     * <p> notFormatted   -> Insert note took by db                                                                </p> *
     *                                                                                                                  *
     *******************************************************************************************************************/
    @Test
    public void beautifyAlcHtml() {
        System.out.println(BuildCommon.restoreSpecialCharacters(BuildCommon.beautifyAlcHtml(notFormatted)));
    }

}
