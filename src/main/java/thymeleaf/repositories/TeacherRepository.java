package thymeleaf.repositories;


import org.springframework.stereotype.Repository;
import thymeleaf.model.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class TeacherRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;

    public List<Teacher> findAll(){
        return  entityManager.createQuery("select t from Teacher t",Teacher.class)
                .getResultList();
    }

    @Transactional
    public void save(Teacher teacher) {
        entityManager.persist(teacher);
    }
}
