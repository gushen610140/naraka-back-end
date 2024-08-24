package icu.sunway.naraka.Mapper;

import icu.sunway.naraka.Entity.DO.Card;
import org.apache.ibatis.annotations.Select;

public interface CardMapper {
    @Select("select * from card where name = #{name}")
    Card get(String name);
}
