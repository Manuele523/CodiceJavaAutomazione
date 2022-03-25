package OggettoTest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ObjectClass {

    private String vbString;
    private Date vbDate;
    private Integer vbInteger;
    private Double vbDouble;
    private Long vbLong;
    private BigDecimal vbBigDecimal;
    private Short vbShort;
    private Boolean vbBoolean;
    private byte[] vbByte;
    private SubObjectClass vbSubObject;
    private List<SubObjectClass> lsSubObject;
    private Map<Integer,String> mapIntegerString;
    private Map<String,Boolean> mapStringBoolean;
    private Map<Long,SubObjectClass> mapLongObject;
    private ObjectEnum vbObjectEnum;


}

