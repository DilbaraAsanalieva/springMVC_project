package thymeleaf.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import thymeleaf.model.Company;
import thymeleaf.model.Course;
import thymeleaf.repositories.CompanyRepository;

//import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;


@Service
@AllArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final EntityManagerFactory entityManagerFactory;

    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    public void save(Company company) {
        System.out.println(company.getCompanyName());
        companyRepository.save(company);
        System.out.println("Company successfully saved!");
    }


    public Company findById(Long companyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
        Company company = entityManager.createQuery("select c from Company c where c.id=?1", Company.class).setParameter(1, companyId).getSingleResult();
//        entityManager.getTransaction().commit();
        entityManager.close();
        return  company;

    }

    public void deleteById(Long companyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Company company = entityManager.find(Company.class, companyId);
        entityManager.remove(company);
        entityManager.getTransaction().commit();
        entityManager.close();
    }


    public void update(long id,Company company){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("update Company c set c.companyName=?1, c.locatedCountry=?2 where c.id=?3")
                .setParameter(1,company.getCompanyName())
                .setParameter(2,company.getLocatedCountry())
                .setParameter(3,id)
                .executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();

    }


}
