import ObjectForTest.Superpratica.RwaWbc;
import ObjectForTest.Superpratica.Superpratica;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static Constants.Constants.statiDaAssegnare;
import static Utils.AddressingFileRule.*;
import static java.util.Objects.nonNull;

public class FindUfficioWBC {

    /*******************************************************************************************************************
    * <p> FIND OFFICE FROM FILE IN 2005/3005                                                                      </p> *
    *                                                                                                                  *
    * <p> SUPER_PRATICA.json    -> Insert into /resources/SUPER_PRATICA.json your practice.json took from mongoDb </p> *
    *                                                                                                                  *
    *******************************************************************************************************************/
    @Test
    public void findOffice() throws IOException {
        BufferedReader reader = Files.newBufferedReader(Paths.get("src/main/resources/SUPER_PRATICA.json"), StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Superpratica superpratica = mapper.readValue(reader, Superpratica.class);

        Map<String, String> office = new HashMap<>();
        if (nonNull(superpratica) && statiDaAssegnare.contains(superpratica.getFiltri().getUltimoStato())) {
            addrFactoring(superpratica, office);
            addrProattivo(superpratica, office);
            addrFilialiEstere(superpratica, office);
            addrFilialiVirtuali(superpratica, office);
            addrRealEstate(superpratica, office);
            addrPlafond(superpratica, office);
            addrFinanzaStrutturata(superpratica, office);
            addrNpl(superpratica, office);
            addrBonis(superpratica, office);

            addrDefault(superpratica, office);

            for (Map.Entry<?, ?> o : office.entrySet()) {
                RwaWbc rwaWbc = superpratica.getRwaWbc();
                System.out.println("L'ufficio corretto per la pratica è " + o.getValue() +
                        " con instradamento " + o.getKey() +
                        "\n----------------------------------------------------------------------" +
                        "\nFilialeAttuale: " + superpratica.getFilialeIsp() +
                        "\nIter: " + (nonNull(rwaWbc) ? rwaWbc.getCodITER() : null) +
                        "\nClasseCompetenzaDeliberativa: " + (nonNull(rwaWbc) ? rwaWbc.getCodClasseCompetenzaDeliberativa() : null) +
                        "\nIndustryRichiedente: " + (nonNull(rwaWbc) ? rwaWbc.getCodIndustryRichiedente() : null) +
                        "\nPuntoOperativoPratica: " + superpratica.getPuntoOperativoPratica());
            }
        } else if (!statiDaAssegnare.contains(superpratica.getFiltri().getUltimoStato())) {
            System.out.println("Pratica non in stato da_assegnare!!");
        }
    }
}
