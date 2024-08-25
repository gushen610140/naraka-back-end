package icu.sunway.naraka.Mapper;

import icu.sunway.naraka.Entity.DO.Card;
import icu.sunway.naraka.Entity.Enum.CardName;
import org.apache.ibatis.annotations.Select;

public interface CardMapper {
    @Select("select * from card where name = #{name}")
    Card get(CardName name);

    @Select("select rage from card where name = #{name}")
    int getRage(CardName name);

    @Select("select cn_name from card where name = #{name}")
    String getCnName(CardName name);
}
