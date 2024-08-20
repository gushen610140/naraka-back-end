package icu.sunway.naraka.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface WaitRoomService {
    void addWaitRoom(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    void getById(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    void checkSpace(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    void getAlL(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    void joinRoom(HttpServletRequest req, HttpServletResponse resp);

    void delete(HttpServletRequest req, HttpServletResponse resp);
}
