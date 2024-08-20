package icu.sunway.naraka.Service.Implement;

import com.google.gson.Gson;
import icu.sunway.naraka.Entity.DO.WaitRoom;
import icu.sunway.naraka.Entity.VO.Result;
import icu.sunway.naraka.Mapper.WaitRoomMapper;
import icu.sunway.naraka.Service.WaitRoomService;
import icu.sunway.naraka.utils.MybatisUtils;
import icu.sunway.naraka.utils.UUIDUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

public class WaitRoomServiceImpl implements WaitRoomService {

    private static final WaitRoomServiceImpl waitRoomService = new WaitRoomServiceImpl();
    private final Gson gson = new Gson();
    private final SqlSession sqlSession = MybatisUtils.getSqlSession();
    private final WaitRoomMapper waitRoomMapper = sqlSession.getMapper(WaitRoomMapper.class);

    public static WaitRoomServiceImpl getInstance() {
        return waitRoomService;
    }

    @Override
    public void addWaitRoom(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String player_1_id = req.getParameter("player_1_id");
        String room_name = req.getParameter("room_name");
        String id = UUIDUtils.generateUUID();
        waitRoomMapper.insert(id, player_1_id, room_name);
        resp.setContentType("application/json");
        resp.getWriter().write(gson.toJson(new Result<>(200, "创建房间成功", id)));
    }

    @Override
    public void getById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        WaitRoom waitRoom = waitRoomMapper.getById(id);
        resp.setContentType("application/json");
        resp.getWriter().write(gson.toJson(new Result<>(200, "获取房间成功", waitRoom)));
    }

    @Override
    public void checkSpace(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        WaitRoom waitRoom = waitRoomMapper.getById(id);
        if (waitRoom.getPlayer_1_id() != null && waitRoom.getPlayer_2_id() != null) {
            resp.getWriter().write(gson.toJson(new Result<>(200, "房间已满", 3)));
        }
        if (waitRoom.getPlayer_1_id() == null && waitRoom.getPlayer_2_id() != null) {
            resp.getWriter().write(gson.toJson(new Result<>(200, "1号位置为空", 1)));
        }
        if (waitRoom.getPlayer_2_id() != null && waitRoom.getPlayer_1_id() == null) {
            resp.getWriter().write(gson.toJson(new Result<>(200, "2号位置为空", 2)));
        }
        resp.getWriter().write(gson.toJson(new Result<>(200, "房间为空", 0)));
    }

    @Override
    public void getAlL(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<WaitRoom> waitRoomList = waitRoomMapper.getAll();
        resp.getWriter().write(gson.toJson(new Result<>(200, "获取房间列表成功", waitRoomList)));
    }

    @Override
    public void joinRoom(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        String player_2_id = req.getParameter("player_2_id");
        waitRoomMapper.update_player_2_id(id, player_2_id);
    }

    @Override
    public void delete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String id = req.getParameter("id");
            waitRoomMapper.delete(id);
        } catch (Exception e) {
            System.out.println("<delete> Error: " + e.getMessage());
        }
    }
}
