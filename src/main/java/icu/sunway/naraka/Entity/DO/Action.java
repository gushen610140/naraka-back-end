package icu.sunway.naraka.Entity.DO;

import icu.sunway.naraka.Entity.Enum.ActionName;
import icu.sunway.naraka.Entity.Enum.ActionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Action {
    private ActionName name;
    private int value;
    private String intro;
    private ActionType type;
}
