package icu.sunway.naraka.Mapper;

import icu.sunway.naraka.Entity.DO.CardPlayer;
import icu.sunway.naraka.Entity.Enum.CardName;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CardPlayerMapper {
    @Select("select * from card_player where player_id = #{player_id}")
    List<CardPlayer> listByPlayer(@Param("player_id") String player_id);

    @Insert("insert into card_player(id, player_id, card_name) values (#{id}, #{player_id}, #{card_name})")
    void add(@Param("id") String id,
             @Param("player_id") String player_id,
             @Param("card_name") CardName card_name);

    @Select("select card_name from card_player where id = #{id}")
    CardName get(@Param("id") String id);

    @Delete("delete from card_player where id = #{id}")
    void remove(@Param("id") String id);

    @Delete("delete from card_player where player_id = #{player_id}")
    void removeByPlayer(@Param("player_id") String player_id);
}
