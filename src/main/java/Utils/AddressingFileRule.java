package Utils;

import ObjectForTest.IndirizzamentoWbc.Indirizzamento;
import ObjectForTest.Superpratica.Superpratica;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static Constants.Constants.*;
import static java.util.Objects.nonNull;

public class AddressingFileRule {

    public static void addrFactoring(Superpratica superpratica, Map<String, String> office) {
        if (MapUtils.isEmpty(office) && checkRwaWbcExist(superpratica)) {
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
                String dirRegionale = superpratica.getDirRegionale();

                if (StringUtils.equalsIgnoreCase(codITER, "21") && StringUtils.isNotEmpty(dirRegionale)) {
                    if (DIREZIONE_REGIONALE_60113.contains(dirRegionale)) {
                        setOffice(office, "FACTORING", "60113");
                    } else if (DIREZIONE_REGIONALE_60114.contains(dirRegionale)) {
                        setOffice(office, "FACTORING", "60114");
                    }
                } else if (StringUtils.equalsIgnoreCase(codITER, "22") && StringUtils.isNotEmpty(dirRegionale)) {
                    if (AREA_CORPORATE_60113.contains(dirRegionale)) {
                        setOffice(office, "FACTORING", "60113");
                    } else if (AREA_CORPORATE_60114.contains(dirRegionale)) {
                        setOffice(office, "FACTORING", "60114");
                    }
                }
            }
        }
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

    public static void addrRealEstate(Superpratica superpratica, Map<String, String> office) {
        if (MapUtils.isEmpty(office) && StringUtils.isNotEmpty(superpratica.getCodFilialeAperturaPratica())) {
            String area = checkRwaWbcExist(superpratica) && StringUtils.isNotBlank(superpratica.getRwaWbc().getCodIndustryRichiedente()) ? "CIB" : "BDT";
            Indirizzamento indirizzamento = indirizzamentoRealEstate.getListaIndirizzamenti()
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

        if (MapUtils.isEmpty(office) && nonNull(codTipoSegmentazione)) {
            if (checkRwaWbcExist(superpratica)) {
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

    public static void addrBonis(Superpratica superpratica, Map<String, String> office) {
        if (MapUtils.isEmpty(office) && apicali.contains(superpratica.getPuntoOperativoPratica())) {
            if (checkRwaWbcExist(superpratica) && nonNull(MAP_INDSUTRY_BONIS_CIB.get(superpratica.getRwaWbc().getCodIndustryRichiedente()))) {
                setOffice(office, "BONIS", MAP_INDSUTRY_BONIS_CIB.get(superpratica.getRwaWbc().getCodIndustryRichiedente()));
            } else if (StringUtils.isNotEmpty(superpratica.getDirRegionale())) {
                setOffice(office, "BONIS", MAP_AREA_TERRITORIALE_BONIS_BDT.get(superpratica.getDirRegionale()));
            }
        }
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

    private static Boolean checkAnagraficaExist(Superpratica superpratica) {
        return nonNull(superpratica.getAnagrafica())
                && nonNull(superpratica.getAnagrafica().getAnagCliente())
                && nonNull(superpratica.getAnagrafica().getAnagCliente().getAnagraficaIsp());
    }

    private static void setOffice(Map<String, String> office, String key, String code) {
        office.put(key, code);
    }

}
