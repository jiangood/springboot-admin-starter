package io.admin.common;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BetweenTests {

    @Test
    void testBetweenGettersAndSetters() {
        Between between = new Between();
        
        // 测试初始值
        assertNull(between.getBegin());
        assertNull(between.getEnd());
        
        // 测试设置值
        between.setBegin("2023-01-01");
        between.setEnd("2023-12-31");
        
        assertEquals("2023-01-01", between.getBegin());
        assertEquals("2023-12-31", between.getEnd());
    }
}