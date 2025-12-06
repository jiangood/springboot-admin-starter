// -------------------------------------------------------------
// src/test/java/io/admin/framework/data/specification/UserRepository.java

package io.admin.framework.data.specification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    // JpaSpecificationExecutor 提供了 findAll(Specification<T> spec) 方法
}