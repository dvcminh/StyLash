package com.vuducminh.stylash.service;

import com.vuducminh.stylash.model.Voucher;

import java.math.BigDecimal;
import java.util.List;

public interface VoucherService {
    Voucher getVoucherByCode(String code);
    BigDecimal getVoucherValueByCode(String voucherCode);
    void useVoucher(Voucher voucher);
    Voucher getVoucherById(Long id);
    List<Voucher> getAllVouchers();
    void updateVoucher(Voucher voucher);
    void deleteVoucher(Long id);
}
