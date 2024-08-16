package icu.sunway.naraka.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface PlayerService {
    void addPlayer(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}
