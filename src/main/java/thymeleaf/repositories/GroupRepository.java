package thymeleaf.repositories;

import org.springframework.stereotype.Repository;
import thymeleaf.model.Group;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GroupRepository {


    @PersistenceContext
    private EntityManager entityManager;


    public GroupRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public List<Group> findAll(){
        return entityManager
                .createQuery("select g from Group g",Group.class)
                .getResultList();
    }


    @Transactional
    public void save(Group group){
        entityManager.persist(group);
    }


    public Group findById(long groupId){
        return entityManager.find(Group.class,groupId);
    }

    public List<Group> getAll(Long id){
        return entityManager.createQuery("select g from Group g",Group.class)
                .getResultList();
    }

    public void update(long id,Group group) {
        entityManager.getTransaction().begin();
        Group group1 = show(id); //findById
        group1.setGroupName(group.getGroupName());
        group1.setDateOfStart(group.getDateOfStart());
        group1.setDateOfFinish(group.getDateOfFinish());
        entityManager.getTransaction().commit();
    }
    List<Group> groups = new ArrayList<>();

    public Group show(long id){
        return groups.stream().filter(group -> group.getId()==id).findAny().orElse(null);
    }
}
