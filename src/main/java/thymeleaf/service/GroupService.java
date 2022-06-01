package thymeleaf.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import thymeleaf.model.Group;
import thymeleaf.repositories.GroupRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
@AllArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final EntityManagerFactory entityManagerFactory;

    public List<Group> findAllGroups() {
        return groupRepository.findAll();
    }

    public void save(Group group) {
        System.out.println(group.getGroupName());
        groupRepository.save(group);
        System.out.println("Group successfully saved!");
    }

    public void deleteById(Long groupId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Group group = entityManager.find(Group.class, groupId);
        entityManager.remove(group);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Group findById(Long groupId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
        Group group = entityManager.createQuery("select g from Group g where g.id=?1", Group.class).setParameter(1, groupId).getSingleResult();
//        entityManager.getTransaction().commit();
        entityManager.close();
        return  group;

    }

    public void update(long id, Group group){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("update Group g set g.groupName=?1, g.dateOfStart=?2,g.dateOfFinish=?3 where g.id=?4")
                .setParameter(1,group.getGroupName())
                .setParameter(2,group.getDateOfStart())
                .setParameter(3,group.getDateOfFinish())
                .setParameter(4,id)
                .executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();

    }







}
