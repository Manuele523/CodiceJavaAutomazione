package OggettoTest;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubObjectClass {

    private String vbString;
    private Date vbDate;
    private Integer vbInteger;
    private int vb_int;
    private Double vbDouble;
    private double vb_double;
    private Long vbLong;
    private long vb_long;
    private BigDecimal vbBigDecimal;
    private Short vbShort;
    private short vb_short;
    private Boolean vbBoolean;
    private boolean vb_boolean;
    private List<Long> lsSubObject;
    private Map<Integer,String> mapIntegerString;
    private ObjectEnum vbSubObjectEnum;

}
