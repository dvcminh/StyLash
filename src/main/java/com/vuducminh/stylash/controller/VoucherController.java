package com.vuducminh.stylash.controller;

import com.vuducminh.stylash.model.Product;
import com.vuducminh.stylash.model.Voucher;
import com.vuducminh.stylash.service.VoucherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vouchers")
public class VoucherController {

    private final VoucherService voucherService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Voucher>> getAllVoucher() {
        List<Voucher> vouchers = voucherService.getAllVouchers();
        return ResponseEntity.ok(vouchers);
    }

    @PostMapping("/check_voucher")
    public BigDecimal getVoucherByCode(@RequestParam("voucherCode") String voucherCode) {
        Voucher voucher = voucherService.getVoucherByCode(voucherCode);
        if (voucher != null) {
            return voucher.getDiscount();
        } else {
            return null;
        }
    }

//    @PostMapping("/{code}/use")
//    public ResponseEntity<Void> useVoucher(@PathVariable("code") String code) {
//        Voucher voucher = voucherService.getVoucherByCode(code);
//        if (voucher != null && voucher.getStatus() == VoucherStatus.UNUSED) {
//            voucherService.useVoucher(voucher);
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
//    }
}