package icu.sunway.naraka.Service.Implement;

import com.google.gson.Gson;
import icu.sunway.naraka.Entity.DO.Action;
import icu.sunway.naraka.Entity.DO.Player;
import icu.sunway.naraka.Entity.Enum.ActionName;
import icu.sunway.naraka.Entity.Enum.CardName;
import icu.sunway.naraka.Entity.VO.Result;
import icu.sunway.naraka.Entity.VO.RoundResult;
import icu.sunway.naraka.Mapper.ActionMapper;
import icu.sunway.naraka.Mapper.CardPlayerMapper;
import icu.sunway.naraka.Mapper.PlayerMapper;
import icu.sunway.naraka.Service.GameLogicService.ActionComputer;
import icu.sunway.naraka.Service.PlayerService;
import icu.sunway.naraka.utils.MybatisUtils;
import icu.sunway.naraka.utils.UUIDUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

public class PlayerServiceImpl implements PlayerService {
    private static final PlayerServiceImpl playerService = new PlayerServiceImpl();
    private final Gson gson = new Gson();
    private final ActionComputer actionComputer = ActionComputer.getInstance();
    private final SqlSession sqlSession = MybatisUtils.getSqlSession();
    private final PlayerMapper playerMapper = sqlSession.getMapper(PlayerMapper.class);
    private final ActionMapper actionMapper = sqlSession.getMapper(ActionMapper.class);
    private final CardPlayerMapper cardPlayerMapper = sqlSession.getMapper(CardPlayerMapper.class);

    public static PlayerServiceImpl getInstance() {
        return playerService;
    }

    @Override
    public void addPlayer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String nickname = req.getParameter("nickname");
        String id = UUIDUtils.generateUUID();
        playerMapper.insert(id, nickname);
        // 开局送一张卡
        cardPlayerMapper.add(UUIDUtils.generateUUID(), id, CardName.randomCardName());
        resp.setContentType("application/json");
        resp.getWriter().write(gson.toJson(new Result<>(200, "添加用户成功", id)));
    }

    @Override
    public void getById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        Player player = playerMapper.getById(id);
        resp.setContentType("application/json");
        resp.getWriter().write(gson.toJson(new Result<>(200, "获取用户成功", player)));
    }

    @Override
    public void updateStatus(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String status = req.getParameter("status");
        playerMapper.updateStatus(id, status);
        resp.getWriter().write(gson.toJson(new Result<>(200, "更改状态成功", true)));
    }

    @Override
    public void confirmAction(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String player_me_id = req.getParameter("player_me_id");
            String player_opponent_id = req.getParameter("player_opponent_id");
            ActionName chosen_action = ActionName.valueOf(req.getParameter("chosen_action"));

            playerMapper.updateChosenAction(player_me_id, chosen_action);

            Player player_me = playerMapper.getById(player_me_id);
            Player player_opponent = playerMapper.getById(player_opponent_id);
            if (player_opponent.getChosen_action() != ActionName.none) {
                // 对手已准备，开始结算伤害
                Action action_me = actionMapper.get(player_me.getChosen_action());
                Action action_opponent = actionMapper.get(player_opponent.getChosen_action());
                RoundResult roundResult = actionComputer.computeAction(player_me, player_opponent, action_me, action_opponent);
                resp.getWriter().write(gson.toJson(new Result<>(200, "对手已经准备", roundResult)));
            } else {
                resp.getWriter().write(gson.toJson(new Result<>(400, "对手未准备", null)));
            }
        } catch (Exception e) {
            System.out.println("<updateChosenAction> Error: " + e.getMessage());
        }
    }

    @Override
    public void remove(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        playerMapper.remove(id);
    }
}
