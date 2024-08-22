package icu.sunway.naraka.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface PlayerService {
    void addPlayer(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    void getById(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    void updateStatus(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    void confirmAction(HttpServletRequest req, HttpServletResponse resp);

    void executeAttack(HttpServletRequest req, HttpServletResponse resp);
}
