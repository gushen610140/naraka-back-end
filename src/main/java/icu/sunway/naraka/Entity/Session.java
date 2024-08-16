package icu.sunway.naraka.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    String id;
    String player_1_id;
    String player_2_id;
    int round;
    String cards;
}
