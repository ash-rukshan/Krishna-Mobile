package lk.krishan_mobile.asset.payment.dao;


import lk.krishan_mobile.asset.payment.entity.Payment;
import lk.krishan_mobile.asset.purchase_order.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentDao extends JpaRepository< Payment,Integer> {
    List< Payment> findByPurchaseOrder(PurchaseOrder purchaseOrder);

    Payment findFirstByOrderByIdDesc();

  List< Payment> findByCreatedAtIsBetween(LocalDateTime from, LocalDateTime to);
  List< Payment> findByCreatedAtIsBetweenAndCreatedBy(LocalDateTime from, LocalDateTime to, String userName);
}
