package ru.easymoneydunker.controller.report;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.easymoneydunker.model.report.DailyReport;
import ru.easymoneydunker.service.report.ReportService;

import java.time.LocalDate;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
@Validated
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/{userId}")
    public ResponseEntity<DailyReport> getDailyReport(@PathVariable("userId") Long userId, @RequestParam("date") LocalDate date) {
        DailyReport report = reportService.generateDailyReport(userId, date);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/journal/{userId}")
    public ResponseEntity<?> getDailyReports(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(reportService.getDailyReportsByUserId(userId));
    }
}
