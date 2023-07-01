package lk.krishan_mobile.asset.invoice_ledger.service;

import lk.krishan_mobile.asset.invoice_ledger.dao.InvoiceLedgerDao;
import lk.krishan_mobile.asset.invoice_ledger.entity.InvoiceLedger;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InvoiceLedgerService {
private final InvoiceLedgerDao invoiceLedgerDao;

  public List< InvoiceLedger > search(InvoiceLedger invoiceLedger) {
    ExampleMatcher matcher = ExampleMatcher
            .matching()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    Example< InvoiceLedger > invoiceLedgerExample = Example.of(invoiceLedger, matcher);
    return invoiceLedgerDao.findAll(invoiceLedgerExample);

  }

  public InvoiceLedgerService(InvoiceLedgerDao invoiceLedgerDao) {
    this.invoiceLedgerDao = invoiceLedgerDao;
  }

  public List< InvoiceLedger> findByCreatedAtIsBetween(LocalDateTime from, LocalDateTime to) {
  return invoiceLedgerDao.findByCreatedAtIsBetween(from,to);
  }

    public InvoiceLedger findByWarrantyNumber(String warrantyNumber) {
    return invoiceLedgerDao.findByWarrantyNumber(warrantyNumber);
    }

  public InvoiceLedger findByEmeiNumber(String emeiNumber) {
    return invoiceLedgerDao.findByEmeiNumber(emeiNumber);
  }
  public InvoiceLedger findById(String Id) {
    return invoiceLedgerDao.findById(Id);
  }


    public InvoiceLedger findByInvoiceId(String invoiceId) {
      return invoiceLedgerDao.findByInvoiceId(invoiceId);
    }


//  public InvoiceLedger findByCode(String findByCode) {
//    return invoiceLedgerDao.findByCode(findByCode);
//  }
}