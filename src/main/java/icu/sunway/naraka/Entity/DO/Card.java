package icu.sunway.naraka.Entity.DO;

import icu.sunway.naraka.Entity.Enum.CardName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    private CardName name;
    private String effect;
    private String intro;
}
