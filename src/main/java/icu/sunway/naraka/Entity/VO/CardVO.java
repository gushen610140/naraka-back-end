package icu.sunway.naraka.Entity.VO;

import icu.sunway.naraka.Entity.Enum.CardName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardVO {
    private String id;
    private CardName name;
    private String intro;
    private int rage;
    private String cn_name;
}
