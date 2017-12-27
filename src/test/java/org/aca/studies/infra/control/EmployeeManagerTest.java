package org.aca.studies.infra.control;

import org.aca.studies.UnitTest;
import org.aca.studies.domain.entity.Employee;
import org.aca.studies.infra.entity.JpaEmployee;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Category(UnitTest.class)
@RunWith(MockitoJUnitRunner.class)
public class EmployeeManagerTest {

    @Mock
    private JpaEmployeeFacade jpaEmployeeFacade;

    private EmployeeManager employeeManager;

    @Before
    public void setUp() {
        employeeManager = new EmployeeManager();
        employeeManager.setJpaEmployeeFacade(jpaEmployeeFacade);
        employeeManager.init();
    }

    @Test
    public void recruitShouldCallToJpaEmployeeOnJpaEmployeeMapperAndCreateOnJpaEmployeeFacade() {
        String name = "Armando";
        BigDecimal salary = BigDecimal.valueOf(1500.0);
        Employee employee = new Employee(name, salary);
        ArgumentCaptor<JpaEmployee> jpaEmployeeArgumentCaptor = ArgumentCaptor.forClass(JpaEmployee.class);
        long expectedNewEmployeeId = 1L;
        when(jpaEmployeeFacade.create(any(JpaEmployee.class))).thenReturn(expectedNewEmployeeId);

        Long newEmployeeId = employeeManager.recruit(employee);

        verify(jpaEmployeeFacade).create(jpaEmployeeArgumentCaptor.capture());

        assertThat(jpaEmployeeArgumentCaptor.getValue().getName()).isEqualTo(name);
        assertThat(jpaEmployeeArgumentCaptor.getValue().getSallary()).isEqualTo(salary);
        assertThat(newEmployeeId).isNotNull();
        assertThat(newEmployeeId).isNotNegative();
        assertThat(newEmployeeId).isEqualTo(expectedNewEmployeeId);
    }

    @Test
    public void resportShouldReturnAListOfEmployees() {
        JpaEmployee employee1 = new JpaEmployee.Builder()
                .withName("Armando")
                .withSallary(BigDecimal.valueOf(1500.0))
                .build();
        JpaEmployee employee2 = new JpaEmployee.Builder()
                .withName("Yamile")
                .withSallary(BigDecimal.valueOf(1800.0))
                .build();
        List<JpaEmployee> jpaEmployees = Arrays.asList(employee1, employee2);

        when(jpaEmployeeFacade.list()).thenReturn(jpaEmployees);

        List<Employee> employees = employeeManager.report();

        verify(jpaEmployeeFacade).list();
        assertThat(employees).isNotEmpty();
        assertThat(employees.size()).isEqualTo(2);
    }
}
