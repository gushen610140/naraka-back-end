package icu.sunway.naraka.Mapper;

import icu.sunway.naraka.Entity.DO.Session;
import org.apache.ibatis.annotations.*;

public interface SessionMapper {
    @Insert("insert into session(id, player_1_id, player_2_id, wait_room_id, status) values (#{id}, #{player_1_id}, #{player_2_id},#{wait_room_id}, 'waiting')")
    void addSession(@Param("id") String id,
                    @Param("player_1_id") String player_1_id,
                    @Param("player_2_id") String player_2_id,
                    @Param("wait_room_id") String wait_room_id);

    @Select("select * from session where id = #{id}")
    Session getOne(@Param("id") String id);

    @Select("select * from session where wait_room_id = #{wait_room_id}")
    Session getByWaitRoomId(@Param("wait_room_id") String wait_room_id);

    @Update("update session set status = #{status} where id = #{id}")
    void updateStatus(@Param("id") String id,
                      @Param("status") String status);

    @Delete("delete from session where id = #{id}")
    void delete(@Param("id") String id);
}
