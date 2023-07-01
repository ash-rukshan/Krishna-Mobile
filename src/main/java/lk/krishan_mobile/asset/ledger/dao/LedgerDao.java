package lk.krishan_mobile.asset.ledger.dao;


import lk.krishan_mobile.asset.item.entity.Item;
import lk.krishan_mobile.asset.item.entity.enums.MainCategory;
import lk.krishan_mobile.asset.ledger.entity.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LedgerDao extends JpaRepository< Ledger, Integer> {
    List<Ledger> findByItem(Item item);

    Ledger findByItemAndSellPrice(Item item, BigDecimal sellPrice);



    List< Ledger > findByCreatedAtBetween(LocalDateTime form, LocalDateTime to);

    List< Ledger > findByMainCategory(MainCategory mainCategory);
}
