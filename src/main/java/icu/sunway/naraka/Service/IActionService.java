package icu.sunway.naraka.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IActionService {
    void getActionList(HttpServletRequest req, HttpServletResponse resp);
}
