package icu.sunway.naraka.Controller.PlayerController;

import icu.sunway.naraka.Service.Implement.PlayerServiceImpl;
import icu.sunway.naraka.Service.PlayerService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/player/attack")
public class UpdateAttack extends HttpServlet {
    final PlayerService playerService = PlayerServiceImpl.getInstance();

    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp) {
        playerService.updateChosenAction(req, resp);
    }
}
