package icu.sunway.naraka.Service.GameLogicService;

import icu.sunway.naraka.Entity.DO.Action;
import icu.sunway.naraka.Entity.DO.Player;
import icu.sunway.naraka.Entity.Enum.ActionName;
import icu.sunway.naraka.Mapper.PlayerMapper;
import icu.sunway.naraka.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

@SuppressWarnings("DuplicateBranchesInSwitch")
public class ActionComputer {
    private static final ActionComputer actionComputer = new ActionComputer();
    private final SqlSession sqlSession = MybatisUtils.getSqlSession();
    private final PlayerMapper playerMapper = sqlSession.getMapper(PlayerMapper.class);

    public static ActionComputer getInstance() {
        return actionComputer;
    }

    public void computeAction(Player player_me, Player player_opponent, Action action_me, Action action_opponent) {
        switch (action_me.getName()) {
            case flick:
                switch (action_opponent.getName()) {
                    case flick:
                        break;
                    case pound:
                        player_me.setHealth_cur(player_me.getHealth_cur() - action_opponent.getValue());
                        break;
                    case bounce:
                        player_opponent.setHealth_cur(player_opponent.getHealth_cur() - action_me.getValue());
                        break;
                    case treat:
                        player_opponent.setHealth_cur(player_opponent.getHealth_cur() - action_me.getValue());
                        break;
                    case rage:
                        player_opponent.setHealth_cur(player_opponent.getHealth_cur() - action_me.getValue());
                        player_opponent.setRage(player_opponent.getRage() + 1);
                        break;
                }
                break;
            case pound:
                switch (action_opponent.getName()) {
                    case flick:
                        player_opponent.setHealth_cur(player_opponent.getHealth_cur() - action_me.getValue());
                        break;
                    case pound:
                        break;
                    case bounce:
                        player_me.setHealth_cur(player_me.getHealth_cur() - action_opponent.getValue());
                        break;
                    case treat:
                        player_opponent.setHealth_cur(player_opponent.getHealth_cur() - action_me.getValue());
                        break;
                    case rage:
                        player_opponent.setHealth_cur(player_opponent.getHealth_cur() - action_me.getValue());
                        player_opponent.setRage(player_opponent.getRage() + 2);
                        break;
                }
                break;
            case bounce:
                switch (action_opponent.getName()) {
                    case flick:
                        player_me.setHealth_cur(player_me.getHealth_cur() - action_opponent.getValue());
                        break;
                    case pound:
                        player_opponent.setHealth_cur(player_opponent.getHealth_cur() - action_me.getValue());
                        break;
                    case bounce:
                        break;
                    case treat:
                        player_opponent.setHealth_cur(player_opponent.getHealth_cur() + action_opponent.getValue());
                        break;
                    case rage:
                        player_opponent.setRage(player_opponent.getRage() + 1);
                        break;
                }
                break;
            case treat:
                switch (action_opponent.getName()) {
                    case flick:
                        player_me.setHealth_cur(player_me.getHealth_cur() - action_opponent.getValue());
                        break;
                    case pound:
                        player_me.setHealth_cur(player_me.getHealth_cur() - action_opponent.getValue());
                        break;
                    case bounce:
                        player_me.setHealth_cur(player_me.getHealth_cur() + action_me.getValue());
                        break;
                    case treat:
                        player_me.setHealth_cur(player_me.getHealth_cur() + action_me.getValue());
                        player_opponent.setHealth_cur(player_opponent.getHealth_cur() + action_opponent.getValue());
                        break;
                    case rage:
                        player_me.setHealth_cur(player_me.getHealth_cur() + action_me.getValue());
                        player_opponent.setRage(player_opponent.getRage() + 1);
                        break;
                }
                break;
            case rage:
                switch (action_opponent.getName()) {
                    case flick:
                        player_me.setHealth_cur(player_me.getHealth_cur() - action_opponent.getValue());
                        player_me.setRage(player_me.getRage() + 1);
                        break;
                    case pound:
                        player_me.setHealth_cur(player_me.getHealth_cur() - action_opponent.getValue());
                        player_me.setRage(player_me.getRage() + 2);
                        break;
                    case bounce:
                        player_me.setRage(player_me.getRage() + 1);
                        break;
                    case treat:
                        player_me.setRage(player_me.getRage() + 1);
                        player_opponent.setHealth_cur(player_opponent.getHealth_cur() + action_opponent.getValue());
                        break;
                    case rage:
                        player_me.setRage(player_me.getRage() + 1);
                        player_opponent.setRage(player_opponent.getRage() + 1);
                        break;
                }
                break;
        }

        playerMapper.updateHealthCur(player_me.getId(), player_me.getHealth_cur());
        playerMapper.updateHealthCur(player_opponent.getId(), player_opponent.getHealth_cur());
        playerMapper.updateRage(player_me.getId(), player_me.getRage());
        playerMapper.updateRage(player_opponent.getId(), player_opponent.getRage());
        playerMapper.updateChosenAction(player_me.getId(), ActionName.none);
        playerMapper.updateChosenAction(player_opponent.getId(), ActionName.none);
    }
}
