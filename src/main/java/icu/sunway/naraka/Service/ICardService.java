package icu.sunway.naraka.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ICardService {
    void getCardListByPlayer(HttpServletRequest req, HttpServletResponse resp);
}
