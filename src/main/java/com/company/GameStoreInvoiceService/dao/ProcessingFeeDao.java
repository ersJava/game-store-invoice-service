package com.company.GameStoreInvoiceService.dao;

import com.company.GameStoreInvoiceService.model.ProcessingFee;

public interface ProcessingFeeDao {

    ProcessingFee getProcessingFee(String itemType);

}
