package icu.sunway.naraka.Controller.CardController;

import icu.sunway.naraka.Service.ICardService;
import icu.sunway.naraka.Service.Implement.CardServiceImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/card/list_by_player")
public class getCardListByPlayer extends HttpServlet {
    ICardService cardService = CardServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        cardService.getCardListByPlayer(req, resp);
    }
}
