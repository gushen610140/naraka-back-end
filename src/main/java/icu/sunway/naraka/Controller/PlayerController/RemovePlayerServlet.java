package icu.sunway.naraka.Controller.PlayerController;

import icu.sunway.naraka.Service.Implement.PlayerServiceImpl;
import icu.sunway.naraka.Service.PlayerService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/player/remove")
public class RemovePlayerServlet extends HttpServlet {
    PlayerService playerService = PlayerServiceImpl.getInstance();

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        playerService.remove(req, resp);
    }
}
