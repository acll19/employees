package org.aca.studies.infra.control;

import org.aca.studies.domain.entity.Employee;
import org.aca.studies.infra.entity.JpaEmployee;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class EmployeeManager {

    private JpaEmployeeMapper jpaEmployeeMapper;

    private JpaEmployeeFacade jpaEmployeeFacade;

    @Inject
    EmployeeManager(JpaEmployeeFacade jpaEmployeeFacade) {
        this.jpaEmployeeMapper = new JpaEmployeeMapper();
        this.jpaEmployeeFacade = jpaEmployeeFacade;
    }

    protected EmployeeManager() {
    }

    public Long recruit(Employee employee) {
        JpaEmployee jpaEmployee = jpaEmployeeMapper.toJpaEmployee(employee);
        return jpaEmployeeFacade.create(jpaEmployee);
    }

    public List<Employee> report() {
        List<JpaEmployee> jpaEmployees = jpaEmployeeFacade.list();
        return jpaEmployees.stream()
                .map(jpaEmployeeMapper::toEmployee)
                .collect(Collectors.toList());
    }
}
