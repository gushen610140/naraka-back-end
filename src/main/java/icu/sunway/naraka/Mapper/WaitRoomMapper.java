package icu.sunway.naraka.Mapper;

import icu.sunway.naraka.Entity.DO.WaitRoom;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface WaitRoomMapper {
    @Insert("insert into wait_room_table(id, player_1_id, status, room_name) values (#{id}, #{player_1_id}, 'waiting', #{room_name})")
    void insert(@Param("id") String id,
                @Param("player_1_id") String player_1_id,
                @Param("room_name") String room_name);

    @Select("select * from wait_room_table where id = #{id}")
    WaitRoom getById(@Param("id") String id);

    @Select("select * from wait_room_table")
    List<WaitRoom> getAll();

    @Update("update wait_room_table set player_2_id = #{player_2_id} where id = #{id}")
    void update_player_2_id(@Param("id") String id,
                            @Param("player_2_id") String player_2_id);

    @Delete("delete from wait_room_table where id = #{id}")
    void delete(@Param("id") String id);
}
