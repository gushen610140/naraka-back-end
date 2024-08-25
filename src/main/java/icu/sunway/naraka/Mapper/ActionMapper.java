package icu.sunway.naraka.Mapper;

import icu.sunway.naraka.Entity.DO.Action;
import icu.sunway.naraka.Entity.Enum.ActionName;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ActionMapper {
    @Select("select * from action where name = #{name}")
    Action get(ActionName name);

    @Select("select * from action")
    List<Action> list();
}
