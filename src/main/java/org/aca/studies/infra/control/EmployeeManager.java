package org.aca.studies.infra.control;

import org.aca.studies.domain.entity.Employee;
import org.aca.studies.infra.entity.JpaEmployee;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class EmployeeManager {

    private JpaEmployeeMapper jpaEmployeeMapper;

    private JpaEmployeeFacade jpaEmployeeFacade;

    @Inject
    public EmployeeManager(JpaEmployeeMapper jpaEmployeeMapper, JpaEmployeeFacade jpaEmployeeFacade) {
        this.jpaEmployeeMapper = jpaEmployeeMapper;
        this.jpaEmployeeFacade = jpaEmployeeFacade;
    }

    public Long recruit(Employee employee) {
        JpaEmployee jpaEmployee = jpaEmployeeMapper.toJpaEmployee(employee);
        return jpaEmployeeFacade.create(jpaEmployee);
    }
}
