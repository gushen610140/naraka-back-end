package icu.sunway.naraka.Entity.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WaitRoom {
    private String id;
    private String player_1_id;
    private String player_2_id;
    private String status;
    private String room_name;
}
