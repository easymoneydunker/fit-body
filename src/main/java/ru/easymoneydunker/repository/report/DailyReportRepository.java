package ru.easymoneydunker.repository.report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.easymoneydunker.model.report.DailyReport;

import java.util.List;

public interface DailyReportRepository extends JpaRepository<DailyReport, Long> {
    @Query("select d from DailyReport d where d.userId = ?1")
    List<DailyReport> findByUserId(Long userId);
}