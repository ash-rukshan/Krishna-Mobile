package lk.crystal.asset.invoice.entity.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InvoicePrintOrNot {
  PRINTED("Print"),
  NOT_PRINTED("Don't Print");
  private final String invoicePrintOrNot;
}
