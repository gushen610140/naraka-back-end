package icu.sunway.naraka.Entity.DO;

import icu.sunway.naraka.Entity.Enum.ActionName;
import icu.sunway.naraka.Entity.Enum.CardName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    String id;
    ActionName chosen_action;
    CardName chosen_card;
    int health_max;
    int health_cur;
    String nickname;
    String status;
    int rage;
}
