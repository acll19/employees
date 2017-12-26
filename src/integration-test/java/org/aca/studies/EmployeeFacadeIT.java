package org.aca.studies;

import org.aca.studies.infra.control.JpaEmployeeFacade;
import org.aca.studies.infra.entity.JpaEmployee;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.wildfly.swarm.arquillian.DefaultDeployment;

import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@Category(IntegrationTest.class)
@RunWith(Arquillian.class)
@DefaultDeployment(type = DefaultDeployment.Type.JAR)
public class EmployeeFacadeIT {

    private String employeeName;
    private double employeeSalary;

    @ArquillianResource
    InitialContext context;

    @Before
    public void setUp() {
        jpaEmployeeFacade.setEntityManager(entityManager);
        employeeName = "Armando";
        employeeSalary = 2500.0;
    }

    @After
    public void tearDown() {
        entityManager.createQuery("DELETE FROM JpaEmployee");
    }

    @Inject
    private JpaEmployeeFacade jpaEmployeeFacade;

    @PersistenceContext(unitName = "TestEmPU")
    private EntityManager entityManager;

    @Test
    public void testDataSourceIsBound() throws Exception {
        DataSource ds = (DataSource) context.lookup("java:jboss/datasources/TestDS");
        assertNotNull(ds);
    }

    @Test
    public void shouldSaveEmployee() {
        JpaEmployee employee = createJpaEmployee("Armando", 2500.0);

        jpaEmployeeFacade.create(employee);

        List<JpaEmployee> jpaEmployees = jpaEmployeeFacade.list();
        assertFalse(jpaEmployees.isEmpty());
    }

    @Test
    public void shouldDeleteEmployee() {
        List<JpaEmployee> beforeJpaEmployees = jpaEmployeeFacade.list();
        JpaEmployee employee = createJpaEmployee(employeeName, employeeSalary);
        Long createdEmployeeId = jpaEmployeeFacade.create(employee);

        jpaEmployeeFacade.delete(createdEmployeeId);

        List<JpaEmployee> afterJpaEmployees = jpaEmployeeFacade.list();
        int expectedDifferenceAfterInsertAndDelete = beforeJpaEmployees.size() - afterJpaEmployees.size();
        assertEquals(expectedDifferenceAfterInsertAndDelete, 0);
    }

    @Test
    public void shouldFindEmployeeById() {
        JpaEmployee employee = createJpaEmployee(employeeName, employeeSalary);
        Long createdEmployeeId = jpaEmployeeFacade.create(employee);

        JpaEmployee expectedJpaEmployee = jpaEmployeeFacade.find(createdEmployeeId);

        assertNotNull(expectedJpaEmployee);
        assertEquals(expectedJpaEmployee, employee);
    }

    @Test
    public void shouldUpdateEmployee() {
        JpaEmployee employee = createJpaEmployee(employeeName, employeeSalary);
        Long createdEmployeeId = jpaEmployeeFacade.create(employee);

        JpaEmployee createdEmployee = jpaEmployeeFacade.find(createdEmployeeId);
        createdEmployee.setName("Roberto");
        jpaEmployeeFacade.update(createdEmployee);
        JpaEmployee updatedEmployee = jpaEmployeeFacade.find(createdEmployeeId);

        assertEquals(updatedEmployee.getName(), createdEmployee.getName());
    }

    private JpaEmployee createJpaEmployee(String name, double salary) {
        return new JpaEmployee.Builder()
                    .withName(name)
                    .withSallary(BigDecimal.valueOf(salary))
                    .build();
    }
}
