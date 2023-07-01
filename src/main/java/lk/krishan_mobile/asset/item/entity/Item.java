package lk.krishan_mobile.asset.item.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFilter;
import lk.krishan_mobile.asset.brand.entity.Brand;
import lk.krishan_mobile.asset.category.entity.Category;
import lk.krishan_mobile.asset.item.entity.enums.WarrantyPeriod;
import lk.krishan_mobile.asset.item_color.entity.ItemColor;
import lk.krishan_mobile.asset.common_asset.model.enums.LiveDead;
import lk.krishan_mobile.asset.item.entity.enums.ItemStatus;
import lk.krishan_mobile.asset.ledger.entity.Ledger;
import lk.krishan_mobile.asset.purchase_order_item.entity.PurchaseOrderItem;
import lk.krishan_mobile.asset.supplier_item.entity.SupplierItem;
import lk.krishan_mobile.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("Item")
public class Item extends AuditEntity {

    @Size( min = 2, message = "Your name cannot be accepted" )
    private String name;

    private String rop;

    @Column( unique = true )
    private String code;

    @Column( nullable = false, precision = 10, scale = 2 )
    private BigDecimal sellPrice;

    @Enumerated( EnumType.STRING )
    private ItemStatus itemStatus;

    @Enumerated(EnumType.STRING)
    private LiveDead liveDead;

    @Enumerated(EnumType.STRING)
    private WarrantyPeriod warrantyPeriod;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Brand brand;

    @ManyToOne
    private ItemColor itemColor;

    @OneToMany( mappedBy = "item" )
    private List< SupplierItem > supplierItem;

    @OneToMany( mappedBy = "item" )
    @JsonBackReference
    private List< Ledger > ledgers;

    @OneToMany( mappedBy = "item" )
    private List< PurchaseOrderItem > purchaseOrderItems;
}
