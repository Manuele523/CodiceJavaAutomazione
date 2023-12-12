package Utils;

import ObjectForTest.IndirizzamentoWbc.IndirizzamentiWbc;
import ObjectForTest.IndirizzamentoWbc.Indirizzamento;
import ObjectForTest.IndirizzamentoWbc.TipoIndirizzamentoEnum;
import ObjectForTest.IndirizzamentoWbc.TypeFactoringEnum;
import ObjectForTest.Superpratica.RwaWbc;
import ObjectForTest.Superpratica.Superpratica;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static Constants.Constants.*;
import static ObjectForTest.IndirizzamentoWbc.TipoIndirizzamentoEnum.*;
import static java.util.Objects.nonNull;

public class AddressingFileRule {

    private static final String FIDEURAM = "03296";
    private static final String ISPB = "03239";

    public static void addrNot01025(Superpratica superpratica, Map<String, String> office) {
        if (MapUtils.isEmpty(office) && checkIfNot01025(superpratica)) {
            String codAbi = superpratica.getAnagrafica().getAnagCliente().getAnagraficaIsp().getCodAbi();
            String uoPrivateBanking = ISPB.equals(codAbi) ? addrUoPrivateBanking(superpratica.getPuntoOperativoPratica()) : addrUoFideuram(superpratica.getPuntoOperativoPratica());
            if (uoPrivateBanking != null) {
                setOffice(office, "PRIVATE_BANKING", uoPrivateBanking);
            }
        }
    }

    private static String addrUoPrivateBanking(String po) {
        return "00022".equals(po) ? "04043" : po;
    }

    private static String addrUoFideuram(String po) {
        return "01911".equals(po) ? "06500" : po;
    }

    public static void addrFactoring(Superpratica superpratica, Map<String, String> office) throws IOException {
        IndirizzamentiWbc indiFactoring = getIndirizzamentiWbc(FACTORING);

        if (MapUtils.isEmpty(office)
                && checkRwaWbcExist(superpratica)) {
            String codITER = superpratica.getRwaWbc().getCodITER();
            String codClasseCompetenzaDeliberativa = superpratica.getRwaWbc().getCodClasseCompetenzaDeliberativa();

            if (Arrays.asList("21", "22").contains(codITER) && StringUtils.equalsIgnoreCase(codClasseCompetenzaDeliberativa, "F")) {
                setOffice(office, "FACTORING", "60212");
            } else if (StringUtils.equalsIgnoreCase("75000", superpratica.getPuntoOperativoPratica())
                    || StringUtils.equalsIgnoreCase("75000", superpratica.getCodUoOriginario())) {
                setOffice(office, "FACTORING", "60114");
            } else if (StringUtils.equalsIgnoreCase(codClasseCompetenzaDeliberativa, "A")
                    && checkAnagraficaExist(superpratica)
                    && nonNull(superpratica.getAnagrafica().getAnagCliente().getAnagraficaIsp().getCodAbi())) {
                String direzioneRegionale = superpratica.getDirRegionale();

                if (StringUtils.equalsIgnoreCase("21", codITER)) {
                    setOffice(office, "FACTORING", getIndirizzamentoComune(indiFactoring, direzioneRegionale, TypeFactoringEnum.DIREZIONE));
                } else if (StringUtils.equalsIgnoreCase("22", codITER)) {
                    setOffice(office, "FACTORING", getIndirizzamentoComune(indiFactoring, direzioneRegionale, TypeFactoringEnum.AREA));
                }
            }
        }
    }

    private static String getIndirizzamentoComune(IndirizzamentiWbc indiFactoring, String direzioneAree, TypeFactoringEnum direzione) {
        String uoFactoring = null;
        List<Indirizzamento> indirizzamenti = indiFactoring
                .getListaIndirizzamenti()
                .stream()
                .filter(indi -> StringUtils.equalsIgnoreCase(direzione.name(), indi.getType()))
                .collect(Collectors.toList());

        if (StringUtils.isNotEmpty(direzioneAree)
                && CollectionUtils.isNotEmpty(indirizzamenti)) {
            List<String> office60113 = getDirezioniAreeFromOffice(indirizzamenti, "60113"),
                    office60114 = getDirezioniAreeFromOffice(indirizzamenti, "60114");

            if (office60113.contains(direzioneAree)) {
                uoFactoring = "60113";
            } else if (office60114.contains(direzioneAree)) {
                uoFactoring = "60114";
            }
        }
        return uoFactoring;
    }

    private static List<String> getDirezioniAreeFromOffice(List<Indirizzamento> lsIndirizzamento, String office) {
        return lsIndirizzamento
                .stream()
                .filter(indi -> StringUtils.equalsIgnoreCase(office, indi.getArea()))
                .findFirst()
                .get()
                .getDirezioniAree();
    }

    public static void addrProattivo(Superpratica superpratica, Map<String, String> office) {
        if (MapUtils.isEmpty(office)) {
            if (apicali.contains(superpratica.getPuntoOperativoPratica())) {
                if (checkRwaWbcExist(superpratica)) {
                    String codITER = superpratica.getRwaWbc().getCodITER();
                    String codClasseCompetenzaDeliberativa = superpratica.getRwaWbc().getCodClasseCompetenzaDeliberativa();

                    for (Map.Entry<String, List<CoppiaITERClasseCompetenzaDeliberativa>> entry : FILIALI_PROATTIVO.entrySet()) {
                        if (entry
                                .getValue()
                                .stream()
                                .anyMatch(c -> StringUtils.equalsIgnoreCase(c.getCodITER(), codITER)
                                        && StringUtils.equalsIgnoreCase(c.getCodClasseCompetenzaDeliberativa(), codClasseCompetenzaDeliberativa))) {
                            setOffice(office, "PROATTIVO", entry.getKey());
                        }
                    }
                }
            } else {
                setOffice(office, "PROATTIVO", superpratica.getPuntoOperativoPratica());
            }
        }
    }

    public static void addrFilialiEstere(Superpratica superpratica, Map<String, String> office) {
        if (MapUtils.isEmpty(office) && !Arrays.asList("NCCAP", "NCAPM", "NFAPM", "NFAPD", "NCRLM", "NCRSM", "NFRLM", "NFRSM", "NFRLD", "NFRSD", "NCRPM", "NFRPM", "NFRPD", "NREVP").contains(superpratica.getCodTipoPratica())) {
            if (checkRwaWbcExist(superpratica) && (iterEstero.contains(superpratica.getRwaWbc().getCodITER()))) {
                setOffice(office, "FILIALI_ESTERE", "60138");
            }
        }
    }

    public static void addrFilialiVirtuali(Superpratica superpratica, Map<String, String> office) {
        if (MapUtils.isEmpty(office) && checkRwaWbcExist(superpratica) && StringUtils.equalsIgnoreCase("82", superpratica.getRwaWbc().getCodITER())) {
            if (StringUtils.equalsIgnoreCase("A", superpratica.getRwaWbc().getCodClasseCompetenzaDeliberativa())) {
                setOffice(office, "FILIALI_VIRTUALI", "13460");
            } else if (StringUtils.equalsIgnoreCase("I", superpratica.getRwaWbc().getCodClasseCompetenzaDeliberativa())) {
                setOffice(office, "FILIALI_VIRTUALI", "13462");
            }
        }
    }

    public static void addrRealEstate(Superpratica superpratica, Map<String, String> office) throws IOException {
        IndirizzamentiWbc indiRealEstate = getIndirizzamentiWbc(REAL_ESTATE);

        if (MapUtils.isEmpty(office)
                && StringUtils.isNotEmpty(superpratica.getCodFilialeAperturaPratica())) {
            String area = checkRwaWbcExist(superpratica) && StringUtils.isNotBlank(superpratica.getRwaWbc().getCodIndustryRichiedente()) ? "CIB" : "BDT";
            Indirizzamento indirizzamento = indiRealEstate.getListaIndirizzamenti()
                    .stream()
                    .filter(ind -> StringUtils.equalsIgnoreCase(area, ind.getArea())
                            && StringUtils.equalsIgnoreCase(superpratica.getCodFilialeAperturaPratica(), ind.getCodFilialeAperturaPratica()))
                    .findFirst()
                    .orElse(null);

            if (nonNull(indirizzamento)) {
                setOffice(office, "REAL_ESTATE", indirizzamento.getUoIndirizzamento());
            }
        }
    }

    public static void addrPlafond(Superpratica superpratica, Map<String, String> office) {
        if (MapUtils.isEmpty(office) && StringUtils.equalsIgnoreCase("07744", superpratica.getFilialeIsp())) {
            setOffice(office, "PLAFOND", "60141");
        }
    }

    public static void addrFinanzaStrutturata(Superpratica superpratica, Map<String, String> office) {

        String codTipoSegmentazione = checkAnagraficaExist(superpratica) ? superpratica.getAnagrafica().getAnagCliente().getAnagraficaIsp().getCodTipoSegmentazione() : null;
        boolean isCib = StringUtils.isNotEmpty(superpratica.getRwaWbc().getCodIndustryRichiedente().trim());

        if (MapUtils.isEmpty(office) && nonNull(codTipoSegmentazione)) {
            if (isCib) {
                if (SEGMENTAZIONE_REGOLAMENTARE_60139.contains(codTipoSegmentazione)) {
                    setOffice(office, "FINANZA_STRUTTURATA", "60139");
                }
            } else {
                if (SEGMENTAZIONE_REGOLAMENTARE_60115.contains(codTipoSegmentazione)) {
                    setOffice(office, "FINANZA_STRUTTURATA", "60115");
                } else if (SEGMENTAZIONE_REGOLAMENTARE_60103.contains(codTipoSegmentazione)) {
                    setOffice(office, "FINANZA_STRUTTURATA", "60103");
                }
            }
        }
    }

    public static void addrNpl(Superpratica superpratica, Map<String, String> office) {
        if (MapUtils.isEmpty(office)
                && StringUtils.isNotEmpty(superpratica.getCodTipoPratica())
                && NUOVA_CONCESSIONE_CORPORATE_ANOMALO.contains(superpratica.getCodTipoPratica())
                && StringUtils.isNotEmpty(superpratica.getCodUoOriginario())
                && UO_NPL_60090.contains(superpratica.getCodUoOriginario())) {
            setOffice(office, "NPL", "60090");
        }
    }

    public static void addrBonis(Superpratica superpratica, Map<String, String> office) throws IOException {
        IndirizzamentiWbc indiBonis = getIndirizzamentiWbc(BANCA_BONIS);

        if(MapUtils.isEmpty(office)
                && nonNull(superpratica)
                && puntoOperativiBonis.contains(superpratica.getPuntoOperativoPratica())) {
            RwaWbc rwaWbc = nonNull(superpratica.getRwaWbc()) ? superpratica.getRwaWbc() : null;
            Boolean isCIB = nonNull(rwaWbc) && StringUtils.isNotBlank(rwaWbc.getCodIndustryRichiedente());

            if(!StringUtils.equalsIgnoreCase("OFF", indiBonis.getAreaActive())) {
                String areaActive = indiBonis.getAreaActive();
                Indirizzamento indirizzamento = null;

                if (Boolean.TRUE.equals(isCIB)
                        && Arrays.asList("ALL", "CIB").contains(areaActive)) {
                    indirizzamento = indiBonis.getListaIndirizzamenti()
                            .stream()
                            .filter(indi -> StringUtils.equalsIgnoreCase(indi.getIndustry(), rwaWbc.getCodIndustryRichiedente()))
                            .findFirst()
                            .orElse(null);
                } else if (StringUtils.isNotEmpty(superpratica.getDirRegionale())
                        && Arrays.asList("ALL", "BDT").contains(areaActive)) {
                    indirizzamento = indiBonis.getListaIndirizzamenti()
                            .stream()
                            .filter(indi -> StringUtils.equalsIgnoreCase(indi.getDirRegionale(), superpratica.getDirRegionale()))
                            .findFirst()
                            .orElse(null);
                }
                setOffice(office, "BONIS", (nonNull(indirizzamento) ? indirizzamento.getUoIndirizzamento() : null));
            }
        }
    }

    private static IndirizzamentiWbc getIndirizzamentiWbc(TipoIndirizzamentoEnum type) throws IOException {
        BufferedReader reader = Files.newBufferedReader(Paths.get("src/main/resources/INDIRIZZAMENTO_WBC/".concat(String.valueOf(type)).concat(".json")), StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(reader, IndirizzamentiWbc.class);
    }

    public static void addrDefault(Superpratica superpratica, Map<String, String> office) {
        if (MapUtils.isEmpty(office)
                && StringUtils.isNotEmpty(superpratica.getCodUoOriginario())
                && !StringUtils.equalsIgnoreCase("07744", superpratica.getCodUoOriginario())) {
            setOffice(office, "NESSUNO", superpratica.getCodUoOriginario());
        }
    }

    private static Boolean checkRwaWbcExist(Superpratica superpratica) {
        return nonNull(superpratica.getRwaWbc())
                && nonNull(superpratica.getRwaWbc().getCodITER())
                && nonNull(superpratica.getRwaWbc().getCodClasseCompetenzaDeliberativa());
    }

    private static Boolean checkIfNot01025(Superpratica superpratica) {
        return nonNull(superpratica.getAnagrafica())
                && nonNull(superpratica.getAnagrafica().getAnagCliente())
                && nonNull(superpratica.getAnagrafica().getAnagCliente().getAnagraficaIsp())
                && !"01025".equals(superpratica.getAnagrafica().getAnagCliente().getAnagraficaIsp().getCodAbi());
    }

    private static Boolean checkAnagraficaExist(Superpratica superpratica) {
        return nonNull(superpratica.getAnagrafica())
                && nonNull(superpratica.getAnagrafica().getAnagCliente())
                && nonNull(superpratica.getAnagrafica().getAnagCliente().getAnagraficaIsp());
    }

    private static void setOffice(Map<String, String> office, String key, String code) {
        office.put(key, code);
    }

}
