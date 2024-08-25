package icu.sunway.naraka.Service.Implement;

import com.google.gson.Gson;
import icu.sunway.naraka.Entity.DO.Action;
import icu.sunway.naraka.Entity.VO.Result;
import icu.sunway.naraka.Mapper.ActionMapper;
import icu.sunway.naraka.Service.IActionService;
import icu.sunway.naraka.utils.MybatisUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ActionServiceImpl implements IActionService {
    public static ActionServiceImpl actionService = new ActionServiceImpl();
    private final Gson gson = new Gson();
    private final SqlSession sqlSession = MybatisUtils.getSqlSession();
    private final ActionMapper actionMapper = sqlSession.getMapper(ActionMapper.class);

    public static ActionServiceImpl getInstance() {
        return actionService;
    }

    @Override
    public void getActionList(HttpServletRequest req, HttpServletResponse resp) {
        List<Action> actionList = actionMapper.list();

        try {
            resp.getWriter().write(gson.toJson(new Result<>(200, "获取动作列表成功", actionList)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
