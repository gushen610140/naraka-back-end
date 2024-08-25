package icu.sunway.naraka.Service.Implement;

import com.google.gson.Gson;
import icu.sunway.naraka.Entity.DO.Card;
import icu.sunway.naraka.Entity.DO.CardPlayer;
import icu.sunway.naraka.Entity.VO.CardVO;
import icu.sunway.naraka.Entity.VO.Result;
import icu.sunway.naraka.Mapper.CardMapper;
import icu.sunway.naraka.Mapper.CardPlayerMapper;
import icu.sunway.naraka.Service.ICardService;
import icu.sunway.naraka.utils.MybatisUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

public class CardServiceImpl implements ICardService {
    private static final CardServiceImpl cardService = new CardServiceImpl();
    private final Gson gson = new Gson();
    private final SqlSession sqlSession = MybatisUtils.getSqlSession();
    private final CardPlayerMapper cardPlayerMapper = sqlSession.getMapper(CardPlayerMapper.class);
    private final CardMapper cardMapper = sqlSession.getMapper(CardMapper.class);

    public static CardServiceImpl getInstance() {
        return cardService;
    }


    @Override
    public void getCardListByPlayer(HttpServletRequest req, HttpServletResponse resp) {
        String player_id = req.getParameter("player_id");
        // 清理缓存，防止相同sql语句不再访问数据库
        sqlSession.clearCache();
        List<CardPlayer> cardPlayerList = cardPlayerMapper.listByPlayer(player_id);
        List<CardVO> cardList = new ArrayList<>();
        for (CardPlayer cardPlayer : cardPlayerList) {
            CardVO card = new CardVO();
            card.setId(cardPlayer.getId());
            Card cardInfo = cardMapper.get(cardPlayer.getCard_name());
            card.setName(cardInfo.getName());
            card.setCn_name(cardInfo.getCn_name());
            card.setRage(cardInfo.getRage());
            card.setIntro(cardInfo.getIntro());
            cardList.add(card);
        }

        try {
            resp.getWriter().write(gson.toJson(new Result<>(200, "获取卡片列表成功", cardList)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
