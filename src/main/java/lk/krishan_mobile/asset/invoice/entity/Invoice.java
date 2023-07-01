package lk.krishan_mobile.asset.invoice.entity;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lk.krishan_mobile.asset.common_asset.model.enums.LiveDead;
import lk.krishan_mobile.asset.customer.entity.Customer;
import lk.krishan_mobile.asset.discount_ratio.entity.DiscountRatio;
import lk.krishan_mobile.asset.invoice.entity.enums.InvoicePrintOrNot;
import lk.krishan_mobile.asset.invoice.entity.enums.InvoiceValidOrNot;
import lk.krishan_mobile.asset.invoice.entity.enums.PaymentMethod;
import lk.krishan_mobile.asset.invoice_ledger.entity.InvoiceLedger;
import lk.krishan_mobile.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("Invoice")
@JsonIgnoreProperties(value = {"balance", "discountAmount", "bankName", "cardNumber"}, allowGetters = true)
public class Invoice extends AuditEntity {

    private String bankName;

    private String cardNumber;

    private String remarks;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Column(precision = 10, scale = 2)
    private BigDecimal discountAmount;

    @Column(precision = 10, scale = 2)
    private BigDecimal amountTendered;

    @Column(precision = 10, scale = 2)
    private BigDecimal balance;
    private InvoicePrintOrNot invoicePrintOrNot;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private InvoiceValidOrNot invoiceValidOrNot;

    @Enumerated(EnumType.STRING)
    private LiveDead liveDead;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private DiscountRatio discountRatio;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "invoice")
    private List< InvoiceLedger > invoiceLedgers;


}
