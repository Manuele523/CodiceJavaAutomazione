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

    private String vbSubString;
    private Date vbSubDate;
    private Integer vbSubInteger;
    private Double dbSubDouble;
    private Long vbSubLong;
    private BigDecimal dvSubBigDecimal;
    private Short vbSubShort;
    private Boolean vbSubBoolean;
    private byte[] vbSubByte;
    private List<Long> lsSubObject;
    private Map<Integer,String> mapIntegerString;
    private ObjectEnum vbSubObjectEnum;

}
