package org.aca.studies.infra.control;

import org.aca.studies.domain.Employee;
import org.aca.studies.infra.entity.JpaEmployee;

class JpaEmployeeMapper {

    Employee toEmployee(JpaEmployee jpaEmployee) {
        return new Employee.Builder()
                .withName(jpaEmployee.getName())
                .withSallary(jpaEmployee.getSallary())
                .build();
    }

    JpaEmployee toJpaEmployee(Employee employee) {
        return new JpaEmployee.Builder()
                .withName(employee.getName())
                .withSallary(employee.getSallary())
                .build();
    }
}
