package icu.sunway.naraka.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface SessionService {
    void addSession(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    void getOne(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    void getByWaitRoomId(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    void delete(HttpServletRequest req, HttpServletResponse resp);
}
