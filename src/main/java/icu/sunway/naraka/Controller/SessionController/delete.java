package icu.sunway.naraka.Controller.SessionController;

import icu.sunway.naraka.Service.Implement.SessionServiceImpl;
import icu.sunway.naraka.Service.SessionService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/session/remove")
public class delete extends HttpServlet {
    final SessionService sessionService = SessionServiceImpl.getInstance();

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        sessionService.delete(req, resp);
    }
}
