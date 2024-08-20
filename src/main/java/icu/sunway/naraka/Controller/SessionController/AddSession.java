package icu.sunway.naraka.Controller.SessionController;

import icu.sunway.naraka.Service.Implement.SessionServiceImpl;
import icu.sunway.naraka.Service.SessionService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/session/add")
public class AddSession extends HttpServlet {
    SessionService sessionService = SessionServiceImpl.getInstance();

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        sessionService.addSession(req, resp);
    }
}
