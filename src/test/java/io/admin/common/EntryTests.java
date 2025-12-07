package io.admin.common;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EntryTests {

    @Test
    void testEntryGettersAndSetters() {
        Entry entry = new Entry();
        
        // 测试初始值
        assertNull(entry.getKey());
        assertNull(entry.getValue());
        
        // 测试设置值
        entry.setKey("test-key");
        entry.setValue("test-value");
        
        assertEquals("test-key", entry.getKey());
        assertEquals("test-value", entry.getValue());
    }

    @Test
    void testEntryWithComplexValue() {
        Entry entry = new Entry();
        
        entry.setKey("user-data");
        Object complexValue = new Object[]{"item1", "item2", "item3"};
        entry.setValue(complexValue);
        
        assertEquals("user-data", entry.getKey());
        assertEquals(complexValue, entry.getValue());
    }
}