package icu.sunway.naraka.Mapper;

import icu.sunway.naraka.Entity.DO.Player;
import icu.sunway.naraka.Entity.Enum.ActionName;
import org.apache.ibatis.annotations.*;

public interface PlayerMapper {
    @Insert("insert into player(id, nickname, status, health_cur, health_max, chosen_action) values (#{id}, #{nickname}, 'not ready', 3000, 3000, 0)")
    void insert(@Param("id") String id,
                @Param("nickname") String nickname);

    @Select("select * from player where id = #{id}")
    Player getById(@Param("id") String id);

    @Update("update player set status = #{status} where id = #{id}")
    void updateStatus(@Param("id") String id,
                      @Param("status") String status);

    @Update("update player set chosen_action = #{chosen_action} where id = #{id}")
    void updateChosenAction(@Param("id") String id,
                            @Param("chosen_action") ActionName chosen_action);

    @Update("update player set health_cur = #{health_cur} where id = #{id}")
    void updateHealthCur(@Param("id") String id,
                         @Param("health_cur") Integer health_cur);

    @Delete("delete from player where id = #{id}")
    void delete(@Param("id") String id);
}
