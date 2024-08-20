package icu.sunway.naraka.Controller.WaitRoomController;

import icu.sunway.naraka.Service.Implement.WaitRoomServiceImpl;
import icu.sunway.naraka.Service.WaitRoomService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/wait_room/remove")
public class delete extends HttpServlet {
    final WaitRoomService waitRoomService = WaitRoomServiceImpl.getInstance();

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        waitRoomService.delete(req, resp);
    }
}
