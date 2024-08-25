package icu.sunway.naraka.Entity.DO;

import icu.sunway.naraka.Entity.Enum.CardName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardPlayer {
    private String id;
    private String player_id;
    private CardName card_name;
}
