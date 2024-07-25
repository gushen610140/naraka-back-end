package icu.sunway.naraka.Mapper;

import icu.sunway.naraka.Entity.Session;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SessionMapper {
    @Select("select * from session")
    List<Session> getSessions();

    @Insert("insert into session(id, player_1_id, player_2_id, round, cards) values (#{id}, #{player_1_id}, #{player_2_id}, 0, '[]')")
    void addSession(@Param("id") String id, @Param("player_1_id") String player_1_id, @Param("player_2_id") String player_2_id);

    @Delete("delete from session where id = #{id}")
    void deleteSession(@Param("id") String id);

    @Update("update session set round = #{round} where id = #{id}")
    void updateRound(@Param("id") String id, @Param("round") int round);
}
