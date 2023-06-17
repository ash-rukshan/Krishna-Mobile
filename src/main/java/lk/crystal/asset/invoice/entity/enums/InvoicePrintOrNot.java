package lk.crystal.asset.invoice.entity.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InvoicePrintOrNot {
  PRINTED("Don't Print"),
  NOT_PRINTED("Print");
  private final String invoicePrintOrNot;
}
