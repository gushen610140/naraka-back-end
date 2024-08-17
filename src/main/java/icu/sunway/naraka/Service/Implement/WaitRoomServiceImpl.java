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

public class WaitRoomServiceImpl implements WaitRoomService {

    private final Gson gson = new Gson();
    private final SqlSession sqlSession = MybatisUtils.getSqlSession();
    private final WaitRoomMapper waitRoomMapper = sqlSession.getMapper(WaitRoomMapper.class);

    private static final WaitRoomServiceImpl waitRoomService = new WaitRoomServiceImpl();
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
}
