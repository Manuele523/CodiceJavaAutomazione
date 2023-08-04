package Constants;

import ObjectForTest.IndirizzamentoWbc.IndirizzamentiWbc;
import ObjectForTest.IndirizzamentoWbc.Indirizzamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

public class Constants {

    public static final List<String> statiDaAssegnare = Arrays.asList("2005", "3005");

    public static final List<String> apicali = Arrays.asList("00003", "02462");
    public static final List<String> iterEstero = Arrays.asList("70", "71", "72");

    public static final Map<String, List<CoppiaITERClasseCompetenzaDeliberativa>> FILIALI_PROATTIVO = initFilialiProattivo();
    public static final IndirizzamentiWbc indirizzamentoRealEstate = initRealEstate();

    public static final List<String> DIREZIONE_REGIONALE_60113 = Arrays.asList("09545", "09546", "09547", "79052", "79022", "55300");
    public static final List<String> DIREZIONE_REGIONALE_60114 = Arrays.asList("09544", "09548", "09549", "09560", "09590", "79007", "79037", "79000");

    public static final Map<String, String> MAP_AREA_TERRITORIALE_BONIS_BDT = initMapBonisBdt();
    public static final Map<String, String> MAP_INDSUTRY_BONIS_CIB = initMapBonisCib();

    public static final List<String> AREA_CORPORATE_60113 = Arrays.asList("01383", "08493", "08492", "08312", "12808", "08684", "09035", "14334", "01595", "08681", "08683", "00797", "00788", "08685", "00791", "00871", "09034", "03706");
    public static final List<String> AREA_CORPORATE_60114 = Collections.singletonList("15206");

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

    private static Map<String, String> initMapBonisBdt() {
        Map<String, String> map = new HashMap<>();

        map.put("09544", "60104");
        map.put("09549", "60104");
        map.put("09560", "60104");
        map.put("09590", "60104");
        map.put("79037", "60104");

        map.put("55300", "60105");
        map.put("09547", "60105");
        map.put("09548", "60105");
        map.put("79007", "60105");
        map.put("79022", "60105");

        map.put("79052", "60106");
        map.put("79000", "60106");
        map.put("09545", "60106");
        map.put("09546", "60106");

        return map;
    }

    private static Map<String, String> initMapBonisCib() {
        Map<String, String> map = new HashMap<>();

        map.put("AUT", "60135");
        map.put("TEL", "60135");
        map.put("SBS", "60135");
        map.put("RCI", "60135");

        map.put("BMH", "60136");
        map.put("EAP", "60136");
        map.put("POW", "60136");

        map.put("FBG", "60137");
        map.put("LUX", "60137");

        map.put("INF", "60140");

        return map;
    }

    private static IndirizzamentiWbc initRealEstate() {
        IndirizzamentiWbc indirizzamentiWbc = new IndirizzamentiWbc();
        List<Indirizzamento> indirizzamento = new ArrayList<>();

        indirizzamento.add(new Indirizzamento("CIB", "40637", "60139"));
        indirizzamento.add(new Indirizzamento("CIB", "37294", "60139"));
        indirizzamento.add(new Indirizzamento("CIB", "40664", "60139"));
        indirizzamento.add(new Indirizzamento("BDT", "40637", "60103"));
        indirizzamento.add(new Indirizzamento("BDT", "37294", "60103"));
        indirizzamento.add(new Indirizzamento("BDT", "40664", "60103"));

        indirizzamentiWbc.setListaIndirizzamenti(indirizzamento);

        return indirizzamentiWbc;
    }

}
