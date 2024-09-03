package icu.sunway.naraka.Service.GameLogicService;

import icu.sunway.naraka.Entity.DO.Action;
import icu.sunway.naraka.Entity.DO.Player;
import icu.sunway.naraka.Entity.Enum.ActionName;
import icu.sunway.naraka.Entity.Enum.CardName;
import icu.sunway.naraka.Entity.VO.RoundResult;
import icu.sunway.naraka.Mapper.ActionMapper;
import icu.sunway.naraka.Mapper.CardMapper;
import icu.sunway.naraka.Mapper.CardPlayerMapper;
import icu.sunway.naraka.Mapper.PlayerMapper;
import icu.sunway.naraka.utils.MybatisUtils;
import icu.sunway.naraka.utils.UUIDUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationTargetException;

@SuppressWarnings({"DuplicateBranchesInSwitch", "DuplicatedCode"})
public class ActionComputer {
    private static final ActionComputer actionComputer = new ActionComputer();
    private final SqlSession sqlSession = MybatisUtils.getSqlSession();
    private final PlayerMapper playerMapper = sqlSession.getMapper(PlayerMapper.class);
    private final CardPlayerMapper cardPlayerMapper = sqlSession.getMapper(CardPlayerMapper.class);
    private final CardMapper cardMapper = sqlSession.getMapper(CardMapper.class);
    private final ActionMapper actionMapper = sqlSession.getMapper(ActionMapper.class);

    public static ActionComputer getInstance() {
        return actionComputer;
    }

    public RoundResult computeAction(Player player_me, Player player_opponent) {

        RoundResult roundResult = new RoundResult();
        Player oldPlayerMe = new Player();
        Player oldPlayerOpponent = new Player();
        // 浅拷贝 Player 对象到 RoundResult 中
        try {
            BeanUtils.copyProperties(oldPlayerMe, player_me);
            BeanUtils.copyProperties(oldPlayerOpponent, player_opponent);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.getMessage());
        }

        // 获取卡片
        String card_me_id = playerMapper.getChosenCard(player_me.getId());
        // 双方选择相同 card 清楚缓存以防止深拷贝
        sqlSession.clearCache();
        String card_opponent_id = playerMapper.getChosenCard(player_opponent.getId());
        CardName card_me = null;
        CardName card_opponent = null;
        if (card_me_id != null) {
            card_me = cardPlayerMapper.get(card_me_id);
        }
        if (card_opponent_id != null) {
            card_opponent = cardPlayerMapper.get(card_opponent_id);
        }

        // 卡牌逻辑0
        if (card_me != null) {
            switch (card_me) {
                case zw:
                    player_opponent.setChosen_action(ActionName.rage);
                    break;
            }
        }
        if (card_opponent != null) {
            switch (card_opponent) {
                case zw:
                    player_me.setChosen_action(ActionName.rage);
                    break;
            }
        }

        // 获取行动
        Action action_me = actionMapper.get(player_me.getChosen_action());
        // 双方选择相同 action 清楚缓存以防止深拷贝
        sqlSession.clearCache();
        Action action_opponent = actionMapper.get(player_opponent.getChosen_action());

        // 更改 Player 中的卡片选择数据 用于 result 返回
        if (card_me != null) {
            String cn_name = cardMapper.getCnName(card_me);
            oldPlayerMe.setChosen_card(cn_name);
        } else {
            oldPlayerMe.setChosen_card("不使用卡牌");
        }
        if (card_opponent != null) {
            String cn_name = cardMapper.getCnName(card_opponent);
            oldPlayerOpponent.setChosen_card(cn_name);
        } else {
            oldPlayerOpponent.setChosen_card("不使用卡牌");
        }
        // 设置旧的 result 数据
        roundResult.setOldPlayerMe(oldPlayerMe);
        roundResult.setOldPlayerOpponent(oldPlayerOpponent);

        // 卡牌逻辑1
        if (card_me != null) {
            switch (card_me) {
                case zhude:
                    if (action_me.getName() == ActionName.treat) {
                        action_me.setValue(action_me.getValue() * 2);
                    }
                    break;
                case cy:
                    player_me.setRage(player_me.getRage() + 4);
                    break;
                case qj:
                    player_me.setRage(player_me.getRage() + 1);
                    break;
                case ssj:
                    if (action_me.getName() == ActionName.flick) {
                        action_me.setValue(action_me.getValue() - 100);
                    }
                    if (action_opponent.getName() == ActionName.pound) {
                        action_opponent.setValue(0);
                    }
                    break;
                case lyh:
                    if (action_me.getName() == ActionName.pound) {
                        action_me.setValue(action_me.getValue() + 100);
                    }
                    break;
            }
        }
        if (card_opponent != null) {
            switch (card_opponent) {
                case zhude:
                    if (action_opponent.getName() == ActionName.treat) {
                        action_opponent.setValue(action_opponent.getValue() * 2);
                    }
                    break;
                case cy:
                    player_opponent.setRage(player_opponent.getRage() + 4);
                    break;
                case qj:
                    player_opponent.setRage(player_opponent.getRage() + 1);
                    break;
                case ssj:
                    if (action_opponent.getName() == ActionName.flick) {
                        action_opponent.setValue(action_opponent.getValue() - 100);
                    }
                    if (action_me.getName() == ActionName.pound) {
                        action_me.setValue(0);
                    }
                    break;
                case lyh:
                    if (action_me.getName() == ActionName.pound) {
                        action_me.setValue(action_me.getValue() + 100);
                    }
                    break;
            }
        }

        // 行动逻辑
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

        // 卡牌逻辑1.5
        if (card_me != null) {
            switch (card_me) {
                case why:
                    player_opponent.setHealth_cur((int) ((oldPlayerMe.getHealth_cur() - player_me.getHealth_cur()) * 0.5));
                    break;
            }
        }
        if (card_opponent != null) {
            switch (card_opponent) {
                case why:
                    player_me.setHealth_cur((int) ((oldPlayerOpponent.getHealth_cur() - player_opponent.getHealth_cur()) * 0.5));
                    break;
            }
        }

        // 卡牌逻辑2
        if (card_me != null) {
            switch (card_me) {
                case cjh:
                    if (action_me.getName() == ActionName.bounce && action_opponent.getName() == ActionName.flick) {
                        player_me.setHealth_cur(oldPlayerMe.getHealth_cur());
                        player_opponent.setHealth_cur(oldPlayerOpponent.getHealth_cur() - 200);
                    }
                    break;
                case lyb:
                    player_me.setHealth_cur(oldPlayerMe.getHealth_cur() - 200);
                    break;
                case bb:
                    player_me.setHealth_cur(oldPlayerMe.getHealth_max());
                    player_me.setRage(0);
                    player_opponent.setHealth_max(oldPlayerOpponent.getHealth_max());
                    break;
                case jyy:
                    player_me.setHealth_cur(player_me.getHealth_cur() + 200);
                    break;
            }
        }
        if (card_opponent != null) {
            switch (card_opponent) {
                case cjh:
                    if (action_opponent.getName() == ActionName.bounce && action_me.getName() == ActionName.flick) {
                        player_opponent.setHealth_cur(oldPlayerOpponent.getHealth_cur());
                        player_me.setHealth_cur(oldPlayerMe.getHealth_cur() - 200);
                    }
                    break;
                case lyb:
                    player_opponent.setHealth_cur(oldPlayerOpponent.getHealth_cur() - 200);
                    break;
                case bb:
                    player_opponent.setHealth_cur(oldPlayerOpponent.getHealth_max());
                    player_opponent.setRage(0);
                    player_me.setHealth_max(oldPlayerOpponent.getHealth_max());
                    break;
                case jyy:
                    player_opponent.setHealth_cur(player_me.getHealth_cur() + 200);
                    break;
            }
        }

        // 血量上限限制
        if (player_me.getHealth_cur() > player_me.getHealth_max()) {
            player_me.setHealth_cur(player_me.getHealth_max());
        }
        if (player_opponent.getHealth_cur() > player_opponent.getHealth_max()) {
            player_opponent.setHealth_cur(player_opponent.getHealth_max());
        }

        // 清除卡片
        if (card_me_id != null) {
            cardPlayerMapper.remove(card_me_id);
        }
        if (card_opponent_id != null) {
            cardPlayerMapper.remove(card_opponent_id);
        }

        // 削减怒气
        if (card_me != null) {
            int rage = cardMapper.getRage(card_me);
            player_me.setRage(player_me.getRage() - rage);
        }
        if (card_opponent_id != null) {
            int rage = cardMapper.getRage(card_opponent);
            player_opponent.setRage(player_opponent.getRage() - rage);
        }

        playerMapper.updateHealthCur(player_me.getId(), player_me.getHealth_cur());
        playerMapper.updateHealthCur(player_opponent.getId(), player_opponent.getHealth_cur());
        playerMapper.updateRage(player_me.getId(), player_me.getRage());
        playerMapper.updateRage(player_opponent.getId(), player_opponent.getRage());
        playerMapper.updateChosenAction(player_me.getId(), ActionName.none);
        playerMapper.updateChosenAction(player_opponent.getId(), ActionName.none);
        playerMapper.updateChosenCard(player_me.getId(), null);
        playerMapper.updateChosenCard(player_opponent.getId(), null);

        cardPlayerMapper.add(UUIDUtils.generateUUID(), player_me.getId(), CardName.randomCardName());
        cardPlayerMapper.add(UUIDUtils.generateUUID(), player_opponent.getId(), CardName.randomCardName());

        roundResult.setNewPlayerMe(player_me);
        roundResult.setNewPlayerOpponent(player_opponent);

        return roundResult;
    }
}
