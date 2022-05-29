import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import thymeleaf.configuration.WebAppConfig;
import thymeleaf.model.Course;
import thymeleaf.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {

        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(WebAppConfig.class);

        EntityManagerFactory managerFactory =
                applicationContext.getBean("emf", EntityManagerFactory.class);
        EntityManager entityManager = managerFactory.createEntityManager();
        entityManager.getTransaction().begin();
//        entityManager.persist(new Course("Dilbara",Fri May 30 08:20:12 MSD 2008));

        entityManager.getTransaction().commit();
        entityManager.close();
        managerFactory.close();
    }
}
