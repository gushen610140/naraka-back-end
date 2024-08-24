package icu.sunway.naraka.Entity.VO;

import icu.sunway.naraka.Entity.DO.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoundResult {
    private Player oldPlayerMe;
    private Player oldPlayerOpponent;
    private Player newPlayerMe;
    private Player newPlayerOpponent;
}
