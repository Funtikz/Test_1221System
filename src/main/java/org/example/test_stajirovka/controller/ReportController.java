package org.example.test_stajirovka.controller;

import lombok.RequiredArgsConstructor;
import org.example.test_stajirovka.dto.DailyReportDto;
import org.example.test_stajirovka.entity.Meal;
import org.example.test_stajirovka.service.api.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/daily/{userId}")
    public ResponseEntity<DailyReportDto> getDailyReport(@PathVariable Long userId, @RequestParam LocalDate date) {
        return ResponseEntity.ok(reportService.getDailyReport(userId, date));
    }

    @GetMapping("/calorie-check/{userId}")
    public ResponseEntity<Boolean> isCalorieLimitExceeded(@PathVariable Long userId, @RequestParam LocalDate date) {
        return ResponseEntity.ok(reportService.isCalorieLimitExceeded(userId, date));
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<Meal>> getMealHistory(
            @PathVariable Long userId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        return ResponseEntity.ok(reportService.getMealHistory(userId, startDate, endDate));
    }
}
