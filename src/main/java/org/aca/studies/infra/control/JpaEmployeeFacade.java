package org.aca.studies.infra.control;

import org.aca.studies.infra.entity.JpaEmployee;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
@Transactional(Transactional.TxType.REQUIRED)
public class JpaEmployeeFacade {

    @PersistenceContext(unitName = "EmPU")
    private EntityManager em;

    public void setEntityManager(EntityManager em){
      this.em = em;
    }


    public Long create(JpaEmployee employee) {
        em.persist(employee);
        return employee.getId();
    }

    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public List<JpaEmployee> list() {
        TypedQuery<JpaEmployee> query = em.createQuery("SELECT e FROM JpaEmployee e", JpaEmployee.class);
        return query.getResultList();
    }

    public void delete(Long employeeId) {
        em.remove(em.find(JpaEmployee.class, employeeId));
    }

    public JpaEmployee find(Long employeeId) {
        return em.find(JpaEmployee.class, employeeId);
    }

    public void update(JpaEmployee employee) {
        em.merge(employee);
    }
}
