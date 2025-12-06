package io.admin.framework.data.query;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatField {
    String name;

    StatType type;

    public static StatField of(String name, StatType type){
        return new StatField(name,type);
    }

    public static StatField sum(String name){
        return new StatField(name,StatType.SUM);
    }

    public static StatField count(String name){
        return new StatField(name,StatType.COUNT);
    }


    public enum StatType {
        SUM,
        COUNT,

        /**
         * 平均
         */
        AVG,

        MIN,
        MAX
    }
}
