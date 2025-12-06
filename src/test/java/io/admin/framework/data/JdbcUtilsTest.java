package io.admin.framework.data;

import io.admin.modules.system.entity.SysOrg;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManagerFactory;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ComponentScan("io.admin.framework.data")
@DataJpaTest
class JdbcUtilsTest {

    @Resource
    JdbcUtils jdbcUtils;



    @Test
    void generateCreateTableSql() {

        String sql = jdbcUtils.generateCreateTableSql(User.class, "user");

        System.out.println(sql);
    }


    @Data
    public static class User {
        String id;
        String name;
        Integer age;

        BigDecimal money;
        Date createTime;
    }
}