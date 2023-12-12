package Constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

public class Constants {

    public static final List<String> statiDaAssegnare = Arrays.asList("2005", "3005");

    public static final List<String> apicali = Arrays.asList("00003", "02462");
    public static final List<String> iterEstero = Arrays.asList("70", "71", "72");

    public static final Map<String, List<CoppiaITERClasseCompetenzaDeliberativa>> FILIALI_PROATTIVO = initFilialiProattivo();

    public static final List<String> puntoOperativiBonis = Arrays.asList("00003", "02462");

    public static final List<String> SEGMENTAZIONE_REGOLAMENTARE_60103 = Arrays.asList("CORED", "SCRED", "SRRED", "COFSR", "SCFSR", "SRFSR", "COFSC", "SCFSC", "COCRE", "SCCRE", "CORE", "SCRE");
    public static final List<String> SEGMENTAZIONE_REGOLAMENTARE_60115 = Arrays.asList("COPF", "SCPF", "COLAF", "SCLAF", "COAF", "SCAF", "COFSP", "SCFSP", "COFSL", "SCFSL", "COFSA", "SCFSA");
    public static final List<String> SEGMENTAZIONE_REGOLAMENTARE_60139 = Arrays.asList("COPF", "SCPF", "COLAF", "SCLAF", "COAF", "SCAF", "COFSP", "SCFSP", "COFSL", "SCFSL", "COFSA", "SCFSA", "CORED", "SCRED", "SRRED", "COFSR", "SCFSR", "SRFSR", "COFSC", "SCFSC", "COCRE", "SCCRE", "CORE", "SCRE");

    public static final String NUOVA_CONCESSIONE_CORPORATE_ANOMALO_ESTERNO = "NCCAE";
    public static final String NUOVA_CONCESSIONE_CORPORATE_ANOMALO_INTERNO = "NCCAI";

    public static final List<String> NUOVA_CONCESSIONE_CORPORATE_ANOMALO = Arrays.asList(NUOVA_CONCESSIONE_CORPORATE_ANOMALO_ESTERNO, NUOVA_CONCESSIONE_CORPORATE_ANOMALO_INTERNO);
    public static final List<String> UO_NPL_60090 = Arrays.asList("02955", "23561");

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CoppiaITERClasseCompetenzaDeliberativa {
        private String codITER;
        private String codClasseCompetenzaDeliberativa;
    }

    private static HashMap<String, List<CoppiaITERClasseCompetenzaDeliberativa>> initFilialiProattivo() {
        HashMap<String, List<CoppiaITERClasseCompetenzaDeliberativa>> filialiProattivo = new HashMap<>();
        List<CoppiaITERClasseCompetenzaDeliberativa> listaCoppie = new ArrayList<>();

        listaCoppie.add(new CoppiaITERClasseCompetenzaDeliberativa("1", "C1"));
        listaCoppie.add(new CoppiaITERClasseCompetenzaDeliberativa("1", "C2"));
        listaCoppie.add(new CoppiaITERClasseCompetenzaDeliberativa("2", "C1"));
        listaCoppie.add(new CoppiaITERClasseCompetenzaDeliberativa("2", "C2"));
        listaCoppie.add(new CoppiaITERClasseCompetenzaDeliberativa("3", "C"));
        listaCoppie.add(new CoppiaITERClasseCompetenzaDeliberativa("8", "C"));
        listaCoppie.add(new CoppiaITERClasseCompetenzaDeliberativa("9", "C"));
        listaCoppie.add(new CoppiaITERClasseCompetenzaDeliberativa("16", "C"));
        listaCoppie.add(new CoppiaITERClasseCompetenzaDeliberativa("17", "C"));
        listaCoppie.add(new CoppiaITERClasseCompetenzaDeliberativa("18", "C"));
        listaCoppie.add(new CoppiaITERClasseCompetenzaDeliberativa("19", "C"));
        listaCoppie.add(new CoppiaITERClasseCompetenzaDeliberativa("20", "C"));
        filialiProattivo.put("60206", listaCoppie);

        listaCoppie = new ArrayList<>();
        listaCoppie.add(new CoppiaITERClasseCompetenzaDeliberativa("11", "C"));
        listaCoppie.add(new CoppiaITERClasseCompetenzaDeliberativa("30", "C"));
        listaCoppie.add(new CoppiaITERClasseCompetenzaDeliberativa("40", "C"));
        listaCoppie.add(new CoppiaITERClasseCompetenzaDeliberativa("50", "C"));
        listaCoppie.add(new CoppiaITERClasseCompetenzaDeliberativa("70", "C"));
        listaCoppie.add(new CoppiaITERClasseCompetenzaDeliberativa("71", "C"));
        listaCoppie.add(new CoppiaITERClasseCompetenzaDeliberativa("72", "C"));
        listaCoppie.add(new CoppiaITERClasseCompetenzaDeliberativa("73", "C"));
        filialiProattivo.put("60208", listaCoppie);

        return filialiProattivo;
    }

}
