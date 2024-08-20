package icu.sunway.naraka.Logic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerStatusResult {
    private String player_1_id;
    private Integer player_1_health_cur_value;
    private DamageType player_1_damage_type;
    private Integer player_1_damage_value;
    private String player_2_id;
    private Integer player_2_health_cur_value;
    private DamageType player_2_damage_type;
    private Integer player_2_damage_value;
}
