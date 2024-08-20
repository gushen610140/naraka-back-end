package icu.sunway.naraka.Entity.DO;

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
    String wait_room_id;
    String status;
}
