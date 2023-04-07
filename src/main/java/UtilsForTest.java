import Utils.BuildCommon;
import org.junit.Test;

public class UtilsForTest {

    private static final String notFormatted = "INSERISCI_NOTA";

    @Test
    public void beautifyAlcHtml() {
        String converted = BuildCommon.restoreSpecialCharacters(BuildCommon.beautifyAlcHtml(notFormatted));
        System.out.println(converted);
    }
}
