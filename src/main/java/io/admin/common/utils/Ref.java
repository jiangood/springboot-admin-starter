package io.admin.common.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ref<T> {

    T current;

    public static <T> Ref<T> createRef() {
        return new Ref<>();
    }

    
}
