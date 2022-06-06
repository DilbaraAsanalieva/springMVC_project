package thymeleaf.repositories;

import org.springframework.stereotype.Repository;
import thymeleaf.model.Course;
import thymeleaf.model.Group;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class GroupRepository {


    @PersistenceContext
    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;

    public List<Group> findAll(){
        return entityManager
                .createQuery("select g from Group g",Group.class)
                .getResultList();
    }

    @Transactional
    public void save(Group group){
//        entityManager.getTransaction().begin();
        entityManager.persist(group);
//        entityManager.getTransaction().commit();
    }

    public Group findById(long groupId){
        return entityManager.find(Group.class,groupId);
    }

    public List<Group> getAll(Long id){
        return entityManager.createQuery("select g from Group g",Group.class)
                .getResultList();
    }
}
