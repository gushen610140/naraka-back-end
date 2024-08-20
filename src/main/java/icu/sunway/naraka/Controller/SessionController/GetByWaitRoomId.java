package icu.sunway.naraka.Controller.SessionController;

import icu.sunway.naraka.Service.Implement.SessionServiceImpl;
import icu.sunway.naraka.Service.SessionService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/session/get_by_wait_room_id")
public class GetByWaitRoomId extends HttpServlet {
    final SessionService sessionService = SessionServiceImpl.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        sessionService.getByWaitRoomId(req, resp);
    }
}
