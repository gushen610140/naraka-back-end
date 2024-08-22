package icu.sunway.naraka.Service.Implement;

import com.google.gson.Gson;
import icu.sunway.naraka.Entity.DO.Action;
import icu.sunway.naraka.Entity.DO.Player;
import icu.sunway.naraka.Entity.DO.Session;
import icu.sunway.naraka.Entity.Enum.ActionName;
import icu.sunway.naraka.Entity.VO.Result;
import icu.sunway.naraka.Logic.DamageLogic;
import icu.sunway.naraka.Logic.PlayerStatusResult;
import icu.sunway.naraka.Mapper.ActionMapper;
import icu.sunway.naraka.Mapper.PlayerMapper;
import icu.sunway.naraka.Mapper.SessionMapper;
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
    private final SqlSession sqlSession = MybatisUtils.getSqlSession();
    private final PlayerMapper playerMapper = sqlSession.getMapper(PlayerMapper.class);
    private final SessionMapper sessionMapper = sqlSession.getMapper(SessionMapper.class);
    private final ActionMapper actionMapper = sqlSession.getMapper(ActionMapper.class);

    public static PlayerServiceImpl getInstance() {
        return playerService;
    }

    @Override
    public void addPlayer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String nickname = req.getParameter("nickname");
        String id = UUIDUtils.generateUUID();
        playerMapper.insert(id, nickname);
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
                switch (action_me.getName()) {
                    case flick:
                        switch (action_opponent.getName()) {
                            case flick:
                                break;
                            case pound:
                                player_me.setHealth_cur(player_me.getHealth_cur() - action_me.getValue());
                        }
                        break;
                    case pound:
                        break;
                }
                resp.getWriter().write(gson.toJson(new Result<>(200, "对手已经准备", true)));
            } else {
                resp.getWriter().write(gson.toJson(new Result<>(400, "对手未准备", false)));
            }
        } catch (Exception e) {
            System.out.println("<updateChosenAction> Error: " + e.getMessage());
        }
    }

    @Override
    public void executeAttack(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String session_id = req.getParameter("session_id");

            Session session = sessionMapper.getOne(session_id);
            String player_1_id = session.getPlayer_1_id();
            String player_2_id = session.getPlayer_2_id();
            Player player_1 = playerMapper.getById(player_1_id);
            Player player_2 = playerMapper.getById(player_2_id);
            PlayerStatusResult result = DamageLogic.compute(player_1, player_2);
            playerMapper.updateChosenAction(player_1_id, ActionName.none);
            playerMapper.updateChosenAction(player_2_id, ActionName.none);
            playerMapper.updateHealthCur(player_1_id, result.getPlayer_1_health_cur_value());
            playerMapper.updateHealthCur(player_2_id, result.getPlayer_2_health_cur_value());
            sessionMapper.updateStatus(session_id, "waiting");

            resp.getWriter().write(gson.toJson(new Result<>(200, "伤害结算成功", result)));
        } catch (Exception e) {
            System.out.println("<attackCompute> Error: " + e.getMessage());
        }
    }
}
