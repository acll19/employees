package org.aca.studies.infra.control;

import org.aca.studies.domain.Employee;
import org.aca.studies.infra.entity.JpaEmployee;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class JpaEmployeeMapperTest {

    private JpaEmployeeMapper jpaEmployeeMapper;
    private String aName;
    private BigDecimal aSallary;

    @Before
    public void setUp() throws Exception {
        jpaEmployeeMapper = new JpaEmployeeMapper();
        aName = "An Employee";
        aSallary = BigDecimal.ONE;
    }

    @Test
    public void shouldCreateAnEmployeeFromAJpaEmployeeInstance() {
        JpaEmployee jpaEmployee = new JpaEmployee.Builder()
                .withName(aName)
                .withSallary(aSallary)
                .build();

        Employee employee = jpaEmployeeMapper.toEmployee(jpaEmployee);

        assertThat(employee).isInstanceOf(Employee.class);
        assertThat(employee.getName()).isEqualTo(aName);
        assertThat(employee.getSallary()).isEqualTo(aSallary);
    }

    @Test
    public void shouldCreateAJpaEmployeeFromAnEmployeeInstance() {
        Employee employee = new Employee.Builder()
                .withName(aName)
                .withSallary(aSallary)
                .build();

        JpaEmployee jpaEmployee = jpaEmployeeMapper.toJpaEmployee(employee);

        assertThat(jpaEmployee).isInstanceOf(JpaEmployee.class);
        assertThat(jpaEmployee.getName()).isEqualTo(aName);
        assertThat(jpaEmployee.getSallary()).isEqualTo(aSallary);
    }
}