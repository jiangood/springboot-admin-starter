package io.admin.common;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataMatrixTests {

    @Test
    void testConstructorWithRowsAndCols() {
        DataMatrix matrix = new DataMatrix(3, 2);
        
        assertEquals(3, matrix.getRowSize());
        assertEquals(2, matrix.getColSize());
        
        // 检查初始化的值是否为null
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                assertNull(matrix.getValue(i, j));
            }
        }
    }

    @Test
    void testAddArray() {
        DataMatrix matrix = new DataMatrix();
        
        Object[] rowData = {"A", "B", "C"};
        matrix.add(rowData);
        
        assertEquals(1, matrix.size());
        assertEquals("A", matrix.getValue(0, 0));
        assertEquals("B", matrix.getValue(0, 1));
        assertEquals("C", matrix.getValue(0, 2));
    }

    @Test
    void testAddIterable() {
        DataMatrix matrix = new DataMatrix();
        
        List<Object> rowData = List.of("X", "Y", "Z");
        matrix.add(rowData);
        
        assertEquals(1, matrix.size());
        assertEquals("X", matrix.getValue(0, 0));
        assertEquals("Y", matrix.getValue(0, 1));
        assertEquals("Z", matrix.getValue(0, 2));
    }

    @Test
    void testGetValueAndSetValue() {
        DataMatrix matrix = new DataMatrix(2, 2);
        
        matrix.setValue(0, 0, "top-left");
        matrix.setValue(1, 1, "bottom-right");
        
        assertEquals("top-left", matrix.getValue(0, 0));
        assertEquals("bottom-right", matrix.getValue(1, 1));
    }

    @Test
    void testIsRowEmpty() {
        DataMatrix matrix = new DataMatrix();
        
        // 空行
        List<Object> emptyRow = List.of("", null, "   ");
        assertTrue(matrix.isRowEmpty(emptyRow));
        
        // 非空行
        List<Object> nonEmptyRow = List.of("", "data", "   ");
        assertFalse(matrix.isRowEmpty(nonEmptyRow));
    }

    @Test
    void testRemoveEmptyRows() {
        DataMatrix matrix = new DataMatrix();
        
        // 添加一些行
        matrix.add(new Object[]{"A", "B"});
        matrix.add(new Object[]{"", ""}); // 空行
        matrix.add(new Object[]{"C", "D"});
        matrix.add(new Object[]{"", null}); // 空行
        
        assertEquals(4, matrix.size());
        
        matrix.removeEmptyRows();
        
        assertEquals(2, matrix.size());
        assertEquals("A", matrix.getValue(0, 0));
        assertEquals("C", matrix.getValue(1, 0));
    }

    @Test
    void testClone() {
        DataMatrix original = new DataMatrix(2, 2);
        original.setValue(0, 0, "original");
        original.setValue(1, 1, "data");
        
        DataMatrix cloned = original.clone();
        
        assertEquals(original.getRowSize(), cloned.getRowSize());
        assertEquals(original.getColSize(), cloned.getColSize());
        assertEquals("original", cloned.getValue(0, 0));
        assertEquals("data", cloned.getValue(1, 1));
        
        // 修改克隆对象不应该影响原始对象
        cloned.setValue(0, 0, "changed");
        assertEquals("original", original.getValue(0, 0));
        assertEquals("changed", cloned.getValue(0, 0));
    }

    @Test
    void testSplit() {
        DataMatrix matrix = new DataMatrix();
        matrix.add(new Object[]{"row1-col1", "row1-col2"});
        matrix.add(new Object[]{"row2-col1", "row2-col2"});
        matrix.add(new Object[]{"row3-col1", "row3-col2"});
        matrix.add(new Object[]{"row4-col1", "row4-col2"});
        matrix.add(new Object[]{"row5-col1", "row5-col2"});
        matrix.add(new Object[]{"row6-col1", "row6-col2"});
        
        List<DataMatrix> splitResult = matrix.split(2);
        
        assertEquals(3, splitResult.size());
        assertEquals(2, splitResult.get(0).size());
        assertEquals(2, splitResult.get(1).size());
        assertEquals(2, splitResult.get(2).size());
    }
}