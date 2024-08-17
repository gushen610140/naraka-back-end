package icu.sunway.naraka.Mapper;

import icu.sunway.naraka.Entity.DO.Player;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface PlayerMapper {
    @Insert("insert into player(id, nickname, status) values (#{id}, #{nickname}, 'not ready')")
    void insert(@Param("id") String id,
                @Param("nickname") String nickname);

    @Select("select * from player where id = #{id}")
    Player getById(@Param("id") String id);
}
