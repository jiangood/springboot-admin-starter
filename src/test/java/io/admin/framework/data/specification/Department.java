package io.admin.framework.data.specification;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "t_dept")
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    // 省略 getter/setter/构造函数
}