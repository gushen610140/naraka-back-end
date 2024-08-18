package icu.sunway.naraka.Controller.WaitRoomController;

import icu.sunway.naraka.Service.Implement.WaitRoomServiceImpl;
import icu.sunway.naraka.Service.WaitRoomService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/wait_room/join")
public class JoinRoomServlet extends HttpServlet {
    WaitRoomService waitRoomService = WaitRoomServiceImpl.getInstance();

    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp) {
        waitRoomService.joinRoom(req, resp);
    }
}
