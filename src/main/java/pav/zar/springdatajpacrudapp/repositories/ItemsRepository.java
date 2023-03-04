package pav.zar.springdatajpacrudapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pav.zar.springdatajpacrudapp.models.Item;
import pav.zar.springdatajpacrudapp.models.Person;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<Item, Integer> {
    List<Item> findByItemName(String itemName);

    //person.getItems
    List<Item> findByOwner(Person owner);
}
