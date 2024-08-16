package icu.sunway.naraka.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface PlayerMapper {
    @Insert("insert into player(id, chosen_action, chosen_card, cards, health_max, health_cur, nickname, status) values (#{id}, '', '', '', 0, 0, #{nickname}, 'not ready')")
    void insert(@Param("id") String id,
                @Param("nickname") String nickname);
}
