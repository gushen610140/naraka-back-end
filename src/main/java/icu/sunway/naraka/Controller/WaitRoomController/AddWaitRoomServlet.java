package icu.sunway.naraka.Controller.WaitRoomController;

import icu.sunway.naraka.Service.Implement.WaitRoomServiceImpl;
import icu.sunway.naraka.Service.WaitRoomService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/wait_room/add")
public class AddWaitRoomServlet extends HttpServlet {
    private final WaitRoomService waitRoomService = WaitRoomServiceImpl.getInstance();

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        waitRoomService.addWaitRoom(req, resp);
    }
}
