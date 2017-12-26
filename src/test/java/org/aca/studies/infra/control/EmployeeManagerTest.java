package org.aca.studies.infra.control;

import org.aca.studies.domain.entity.Employee;
import org.aca.studies.infra.entity.JpaEmployee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeManagerTest {

    @Mock
    private JpaEmployeeFacade jpaEmployeeFacade;

    @Mock
    private JpaEmployeeMapper jpaEmployeeMapper;

    private EmployeeManager employeeManager;

    @Before
    public void setUp() {
        employeeManager = new EmployeeManager(jpaEmployeeMapper, jpaEmployeeFacade);
    }

    @Test
    public void recruitShouldCallToJpaEmployeeOnJpaEmployeeMapperAndCreateOnJpaEmployeeFacade() {
        String name = "Armando";
        BigDecimal salary = BigDecimal.valueOf(1500.0);
        Employee employee = new Employee(name, salary);
        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
        ArgumentCaptor<JpaEmployee> jpaEmployeeArgumentCaptor = ArgumentCaptor.forClass(JpaEmployee.class);
        long expectedNewEmployeeId = 1L;
        when(jpaEmployeeFacade.create(any(JpaEmployee.class))).thenReturn(expectedNewEmployeeId);
        JpaEmployee jpaEmployee = new JpaEmployee.Builder()
                .withName(employee.getName())
                .withSallary(employee.getSallary())
                .build();
        when(jpaEmployeeMapper.toJpaEmployee(any(Employee.class))).thenReturn(jpaEmployee);

        Long newEmployeeId = employeeManager.recruit(employee);

        verify(jpaEmployeeMapper).toJpaEmployee(employeeArgumentCaptor.capture());
        verify(jpaEmployeeFacade).create(jpaEmployeeArgumentCaptor.capture());

        assertThat(employeeArgumentCaptor.getValue().getName()).isEqualTo(name);
        assertThat(employeeArgumentCaptor.getValue().getSallary()).isEqualTo(salary);
        assertThat(jpaEmployeeArgumentCaptor.getValue().getName()).isEqualTo(name);
        assertThat(jpaEmployeeArgumentCaptor.getValue().getSallary()).isEqualTo(salary);
        assertThat(newEmployeeId).isNotNull();
        assertThat(newEmployeeId).isNotNegative();
        assertThat(newEmployeeId).isEqualTo(expectedNewEmployeeId);
    }
}
