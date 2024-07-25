package icu.sunway.naraka.Entity;

import lombok.Data;

@Data
public class Session {
    String id;
    String player_1_id;
    String player_2_id;
    int round;
    String cards;
}
