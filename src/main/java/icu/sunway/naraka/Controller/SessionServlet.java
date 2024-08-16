package icu.sunway.naraka.Controller;

import com.google.gson.Gson;
import icu.sunway.naraka.Entity.VO.Result;
import icu.sunway.naraka.Entity.Session;
import icu.sunway.naraka.Mapper.SessionMapper;
import icu.sunway.naraka.utils.MybatisUtils;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/api/session")
public class SessionServlet extends HttpServlet {

    private final Gson gson = new Gson();
    private final SqlSession sqlSession = MybatisUtils.getSqlSession();
    SessionMapper sessionMapper = sqlSession.getMapper(SessionMapper.class);

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        List<Session> sessionList = sessionMapper.getSessions();
        Result<List<Session>> result = new Result<>(200, "获取会话成功", sessionList);
        res.setContentType("application/json");
        res.getWriter().write(gson.toJson(result));
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        UUID uuid = UUID.randomUUID();
        sessionMapper.addSession(uuid.toString(), req.getParameter("player_1_id"), req.getParameter("player_2_id"));
    }

    public void doDelete(HttpServletRequest req, HttpServletResponse res) {
        sessionMapper.deleteSession(req.getParameter("id"));
    }

    public void doPut(HttpServletRequest req, HttpServletResponse res) {
        List<Session> sessionList = sessionMapper.getSessions();
        int cur_round = 0;
        for (Session session : sessionList) {
            if (session.getId().equals(req.getParameter("id"))) {
                cur_round = session.getRound();
            }
        }
        sessionMapper.updateRound(req.getParameter("id"), cur_round + 1);
    }
}
