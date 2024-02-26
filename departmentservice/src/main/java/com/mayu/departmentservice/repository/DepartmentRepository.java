package com.mayu.departmentservice.repository;

import com.mayu.departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    Department findBygetDepartmentCode(String code);
}
