package org.example.test_stajirovka.service.api;

import org.example.test_stajirovka.dto.DailyReportDto;
import org.example.test_stajirovka.entity.Meal;

import java.time.LocalDate;
import java.util.List;

/**
 * Сервис для генерации отчетов и проверки информации о приеме пищи.
 * Содержит методы для получения ежедневных отчетов, проверки лимита калорий и истории приемов пищи.
 */
public interface ReportService {

    /**
     * Получить ежедневный отчет о потребленных калориях для пользователя на определенную дату.
     *
     * @param userId идентификатор пользователя, для которого нужно получить отчет
     * @param date дата, на которую требуется сформировать отчет
     * @return объект {@link DailyReportDto}, содержащий информацию о потребленных калориях и приемах пищи
     */
    DailyReportDto getDailyReport(Long userId, LocalDate date);

    /**
     * Проверить, превышен ли лимит калорий для пользователя на определенную дату.
     *
     * @param userId идентификатор пользователя, для которого необходимо проверить лимит калорий
     * @param date дата, на которую нужно проверить лимит калорий
     * @return {@code true}, если лимит калорий превышен, иначе {@code false}
     */
    boolean isCalorieLimitExceeded(Long userId, LocalDate date);

    /**
     * Получить историю приемов пищи пользователя за определенный период времени.
     *
     * @param userId идентификатор пользователя, для которого требуется история приемов пищи
     * @param startDate дата начала периода
     * @param endDate дата окончания периода
     * @return список приемов пищи пользователя в указанном периоде
     */
    List<Meal> getMealHistory(Long userId, LocalDate startDate, LocalDate endDate);
}
