package io.admin;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

public class BuildInfoTests {

    @BeforeEach
    void setUp() throws Exception {
        // 使用反射清除静态变量，确保测试独立性
        Field versionField = BuildInfo.class.getDeclaredField("version");
        versionField.setAccessible(true);
        versionField.set(null, null);
        
        Field timeField = BuildInfo.class.getDeclaredField("time");
        timeField.setAccessible(true);
        timeField.set(null, null);
    }

    @Test
    void testGetFrameworkVersion() {
        String version = BuildInfo.getFrameworkVersion();
        // 版本号应该不为null（即使文件不存在也应该有默认值或抛出异常）
        assertNotNull(version);
    }

    @Test
    void testGetFrameworkBuildTime() {
        String time = BuildInfo.getFrameworkBuildTime();
        // 构建时间应该不为null
        assertNotNull(time);
    }
}