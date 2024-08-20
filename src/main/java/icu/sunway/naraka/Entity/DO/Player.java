package icu.sunway.naraka.Entity.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    String id;
    Integer chosen_action;
    String chosen_card;
    String cards;
    Integer health_max;
    Integer health_cur;
    String nickname;
    String status;
}
