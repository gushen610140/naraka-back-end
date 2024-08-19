package icu.sunway.naraka.Service.Implement;

import com.google.gson.Gson;
import icu.sunway.naraka.Entity.DO.Player;
import icu.sunway.naraka.Entity.VO.Result;
import icu.sunway.naraka.Mapper.PlayerMapper;
import icu.sunway.naraka.Service.PlayerService;
import icu.sunway.naraka.utils.MybatisUtils;
import icu.sunway.naraka.utils.UUIDUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

public class PlayerServiceImpl implements PlayerService {
    private final Gson gson = new Gson();
    private final SqlSession sqlSession = MybatisUtils.getSqlSession();
    private final PlayerMapper playerMapper = sqlSession.getMapper(PlayerMapper.class);

    private static final PlayerServiceImpl playerService = new PlayerServiceImpl();
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
}
