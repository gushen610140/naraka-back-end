package icu.sunway.naraka.Service.Implement;

import com.google.gson.Gson;
import icu.sunway.naraka.Entity.DO.Session;
import icu.sunway.naraka.Entity.VO.Result;
import icu.sunway.naraka.Mapper.PlayerMapper;
import icu.sunway.naraka.Mapper.SessionMapper;
import icu.sunway.naraka.Service.SessionService;
import icu.sunway.naraka.utils.MybatisUtils;
import icu.sunway.naraka.utils.UUIDUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

public class SessionServiceImpl implements SessionService {

    final static SessionServiceImpl sessionService = new SessionServiceImpl();
    final Gson gson = new Gson();
    final SqlSession sqlSession = MybatisUtils.getSqlSession();
    final SessionMapper sessionMapper = sqlSession.getMapper(SessionMapper.class);
    final PlayerMapper playerMapper = sqlSession.getMapper(PlayerMapper.class);

    public static SessionServiceImpl getInstance() {
        return sessionService;
    }

    @Override
    public void addSession(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = UUIDUtils.generateUUID();
        String player_1_id = req.getParameter("player_1_id");
        String player_2_id = req.getParameter("player_2_id");
        String wait_room_id = req.getParameter("wait_room_id");
        sessionMapper.addSession(id, player_1_id, player_2_id, wait_room_id);
        resp.getWriter().write(gson.toJson(new Result<>(200, "增加会话成功", id)));
    }

    @Override
    public void getOne(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        Session session = sessionMapper.getOne(id);
        resp.getWriter().write(gson.toJson(new Result<>(200, "获取会话成功", session)));
    }

    @Override
    public void getByWaitRoomId(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String wait_room_id = req.getParameter("wait_room_id");
        Session session = sessionMapper.getByWaitRoomId(wait_room_id);
        if (session == null) {
            resp.getWriter().write(gson.toJson(new Result<>(400, "获取会话失败", null)));
        } else {
            resp.getWriter().write(gson.toJson(new Result<>(200, "获取会话成功", session)));
        }
    }

    @Override
    public void delete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String id = req.getParameter("id");
            Session session = sessionMapper.getOne(id);
            playerMapper.remove(session.getPlayer_1_id());
            playerMapper.remove(session.getPlayer_2_id());
            sessionMapper.delete(id);
        } catch (Exception e) {
            System.out.println("<delete> Error: " + e.getMessage());
        }
    }

}
