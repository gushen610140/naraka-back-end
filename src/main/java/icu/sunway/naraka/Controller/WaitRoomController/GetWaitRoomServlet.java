package icu.sunway.naraka.Controller.WaitRoomController;

import icu.sunway.naraka.Service.Implement.WaitRoomServiceImpl;
import icu.sunway.naraka.Service.WaitRoomService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/wait_room/get")
public class GetWaitRoomServlet extends HttpServlet {
    private final WaitRoomService waitRoomService = WaitRoomServiceImpl.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        waitRoomService.getById(req, resp);
    }
}
