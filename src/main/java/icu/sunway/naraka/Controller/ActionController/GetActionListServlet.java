package icu.sunway.naraka.Controller.ActionController;

import icu.sunway.naraka.Service.IActionService;
import icu.sunway.naraka.Service.Implement.ActionServiceImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/action/list")
public class GetActionListServlet extends HttpServlet {
    final IActionService actionService = ActionServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        actionService.getActionList(req, resp);
    }
}
