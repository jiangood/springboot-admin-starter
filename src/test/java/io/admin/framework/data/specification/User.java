// src/test/java/io/admin/framework/data/specification/User.java

package io.admin.framework.data.specification;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "t_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    
    private Integer age;
    
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    private Department dept;
    
    // 省略 getter/setter/构造函数 (使用 @Data 简化)
}
