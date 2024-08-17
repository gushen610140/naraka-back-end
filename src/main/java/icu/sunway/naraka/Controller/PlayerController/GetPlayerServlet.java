package icu.sunway.naraka.Controller.PlayerController;

import icu.sunway.naraka.Service.Implement.PlayerServiceImpl;
import icu.sunway.naraka.Service.PlayerService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/player/get")
public class GetPlayerServlet extends HttpServlet {

    private final PlayerService playerService = PlayerServiceImpl.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        playerService.getById(req, resp);
    }
}
