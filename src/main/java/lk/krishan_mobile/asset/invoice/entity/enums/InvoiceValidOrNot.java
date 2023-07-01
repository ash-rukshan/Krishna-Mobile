package lk.krishan_mobile.asset.invoice.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InvoiceValidOrNot {
    VALID("Valid"),
    NOTVALID("Not Valid");
    private final String invoiceValidOrNot;
}
