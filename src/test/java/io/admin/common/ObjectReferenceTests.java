package io.admin.common;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ObjectReferenceTests {

    @Test
    void testCreateRef() {
        ObjectReference<String> ref = ObjectReference.createRef();
        
        assertNotNull(ref);
        assertNull(ref.getCurrent());
    }

    @Test
    void testGetCurrentAndSetCurrent() {
        ObjectReference<Integer> ref = ObjectReference.createRef();
        
        // 初始值应该是null
        assertNull(ref.getCurrent());
        
        // 设置值
        ref.setCurrent(42);
        
        assertEquals(Integer.valueOf(42), ref.getCurrent());
        
        // 设置新值
        ref.setCurrent(100);
        
        assertEquals(Integer.valueOf(100), ref.getCurrent());
    }

    @Test
    void testGenericTypes() {
        ObjectReference<String> stringRef = ObjectReference.createRef();
        ObjectReference<Integer> intRef = ObjectReference.createRef();
        
        stringRef.setCurrent("Hello");
        intRef.setCurrent(123);
        
        assertEquals("Hello", stringRef.getCurrent());
        assertEquals(Integer.valueOf(123), intRef.getCurrent());
    }
}