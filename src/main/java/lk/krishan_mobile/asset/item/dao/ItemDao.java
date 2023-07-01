package lk.krishan_mobile.asset.item.dao;


import lk.krishan_mobile.asset.category.entity.Category;
import lk.krishan_mobile.asset.item.entity.Item;
import lk.krishan_mobile.asset.item_color.entity.ItemColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemDao extends JpaRepository<Item, Integer> {
    Item findFirstByOrderByIdDesc();

    List<Item> findByCategory(Category category);


    List<Item> findByItemColor(ItemColor itemColor);
}
