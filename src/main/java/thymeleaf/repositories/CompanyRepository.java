package thymeleaf.repositories;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import thymeleaf.model.Company;

import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Repository
public class CompanyRepository {

    @PersistenceContext
    private final EntityManager entityManager;
//    private EntityManagerFactory entityManagerFactory;

    public List<Company> findAll(){
        return entityManager
                .createQuery("select c from Company c", Company.class)
                .getResultList();
    }

    @Transactional
    public void save(Company company){
//        entityManager.getTransaction().begin();
        entityManager.persist(company);
//        entityManager.getTransaction().commit();
//        entityManager.close();
    }

}
