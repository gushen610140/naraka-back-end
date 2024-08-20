package icu.sunway.naraka.Controller.SessionController;

import icu.sunway.naraka.Service.Implement.SessionServiceImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/api/session/get")
public class GetSession extends HttpServlet {
    final SessionServiceImpl sessionService = SessionServiceImpl.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        sessionService.getOne(req,resp);
    }
}
