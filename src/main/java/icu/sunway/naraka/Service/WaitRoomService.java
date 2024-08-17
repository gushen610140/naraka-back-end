package icu.sunway.naraka.Service;

import icu.sunway.naraka.Entity.DO.WaitRoom;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface WaitRoomService {
    void addWaitRoom(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    void getById(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}
