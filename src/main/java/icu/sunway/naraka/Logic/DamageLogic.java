package icu.sunway.naraka.Logic;

import icu.sunway.naraka.Entity.DO.Player;

public class DamageLogic {

    static Integer whackValue = 500;
    static Integer tapValue = 300;
    static Integer vibrateValue = 600;

    public static PlayerStatusResult compute(Player player_1, Player player_2) {
//        if (Objects.equals(player_1.getChosen_action(), player_2.getChosen_action())) {
//            return new PlayerStatusResult(
//                    player_1.getId(), player_1.getHealth_cur(), DamageType.directive_harm, 0,
//                    player_2.getId(), player_2.getHealth_cur(), DamageType.directive_harm, 0
//            );
//        } else if (player_1.getChosen_action() == 1 && player_2.getChosen_action() == 2) {
//            return new PlayerStatusResult(
//                    player_1.getId(), player_1.getHealth_cur() - whackValue, DamageType.directive_harm, whackValue,
//                    player_2.getId(), player_2.getHealth_cur(), DamageType.directive_harm, 0
//            );
//        } else if (player_1.getChosen_action() == 1 && player_2.getChosen_action() == 3) {
//            return new PlayerStatusResult(
//                    player_1.getId(), player_1.getHealth_cur(), DamageType.directive_harm, 0,
//                    player_2.getId(), player_2.getHealth_cur() - tapValue, DamageType.directive_harm, tapValue
//            );
//        } else if (player_1.getChosen_action() == 2 && player_2.getChosen_action() == 1) {
//            return new PlayerStatusResult(
//                    player_1.getId(), player_1.getHealth_cur(), DamageType.directive_harm, 0,
//                    player_2.getId(), player_2.getHealth_cur() - whackValue, DamageType.directive_harm, whackValue
//            );
//        } else if (player_1.getChosen_action() == 2 && player_2.getChosen_action() == 3) {
//            return new PlayerStatusResult(
//                    player_1.getId(), player_1.getHealth_cur() - vibrateValue, DamageType.directive_harm, vibrateValue,
//                    player_2.getId(), player_2.getHealth_cur(), DamageType.directive_harm, 0
//            );
//        } else if (player_1.getChosen_action() == 3 && player_2.getChosen_action() == 1) {
//            return new PlayerStatusResult(
//                    player_1.getId(), player_1.getHealth_cur() - tapValue, DamageType.directive_harm, tapValue,
//                    player_2.getId(), player_2.getHealth_cur(), DamageType.directive_harm, 0
//            );
//        } else if (player_1.getChosen_action() == 3 && player_2.getChosen_action() == 2) {
//            return new PlayerStatusResult(
//                    player_1.getId(), player_1.getHealth_cur(), DamageType.directive_harm, 0,
//                    player_2.getId(), player_2.getHealth_cur() - vibrateValue, DamageType.directive_harm, vibrateValue
//            );
//        }
        return new PlayerStatusResult();
    }
}
