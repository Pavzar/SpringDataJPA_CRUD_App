package pav.zar.springdatajpacrudapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pav.zar.springdatajpacrudapp.models.Item;
import pav.zar.springdatajpacrudapp.models.Person;
import pav.zar.springdatajpacrudapp.repositories.ItemsRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ItemsService {

    private final ItemsRepository itemsRepository;

    @Autowired
    public ItemsService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public List<Item> findAllItems(){
        return itemsRepository.findAll();
    }

    public Item findOneItem(int id){
        Optional<Item> item = itemsRepository.findById(id);
        return item.orElse(null);
    }

    public List<Item> findByItemName(String itemName){
        return itemsRepository.findByItemName(itemName);
    }

    public List<Item> findByOwner(Person owner){
        return itemsRepository.findByOwner(owner);
    }

    @Transactional
    public void save(Item item){
        itemsRepository.save(item);
    }

    @Transactional
    public void update(int id, Item updatedItem){
        updatedItem.setId(id);
        itemsRepository.save(updatedItem);
    }

    @Transactional
    public void delete(int id){
        itemsRepository.deleteById(id);
    }
}
