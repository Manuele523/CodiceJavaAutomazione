package OggettoTest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public enum ObjectEnum {

    ENUME1("code_1"), ENUME2("code_2");

    private String code;
}
