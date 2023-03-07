package pav.zar.springdatajpacrudapp.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pav.zar.springdatajpacrudapp.models.Person;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PersonDAO {

    private final EntityManager entityManager;

    @Autowired
    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public void testNPlus1() {
        try (Session session = entityManager.unwrap(Session.class)) {

//            //  1 query to db
//            List<Person> people = session.createQuery("select p from Person p", Person.class).getResultList();
//
//            // N queries to db
//            for (Person person : people) {
//                System.out.println("Person: " + person.getName() + " has:" + person.getItems());
//            }

            // Solution for N + 1 problem using left join HQL
            Set<Person> people = new HashSet<Person>(session.createQuery("select p from Person p left join fetch p.items")
                    .getResultList());

            for(Person person : people){
                System.out.println("Person: " + person.getName() + " has:" + person.getItems());
            }
        }
    }

}
