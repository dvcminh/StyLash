package com.vuducminh.stylash.repository;

import com.vuducminh.stylash.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {

    // Các phương thức truy vấn dữ liệu tùy ý

}
