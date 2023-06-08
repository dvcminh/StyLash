package com.vuducminh.stylash.service;

import com.vuducminh.stylash.model.Voucher;
import com.vuducminh.stylash.repository.VoucherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VoucherServiceImpl implements VoucherService{
    private final VoucherRepository voucherRepository;

    @Override
    public Voucher getVoucherByCode(String code) {
        return voucherRepository.findByCode(code);
    }

    @Override
    public BigDecimal getVoucherValueByCode(String voucherCode) {
        Voucher voucher = voucherRepository.findByCode(voucherCode);
        if(voucher!=null){
            return voucher.getDiscount();
        }
        else {
            return null;
        }
    }

    public void useVoucher(Voucher voucher) {
        voucherRepository.save(voucher);
    }

    @Override
    public Voucher getVoucherById(Long id) {
        return null;
    }

    @Override
    public List<Voucher> getAllVouchers() {
        return voucherRepository.findAll();
    }

    @Override
    public void updateVoucher(Voucher voucher) {

    }

    @Override
    public void deleteVoucher(Long id) {

    }
}
