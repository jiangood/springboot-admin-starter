package io.github.jiangood.sa.framework;

public class CodeAssert {


    public static void state(boolean state, int code, String msg) {
        if (!state) {
            throw new CodeException(code, msg);
        }
    }
}
