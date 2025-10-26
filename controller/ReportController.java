package project.controller;

import project.entity.Report;
import java.time.LocalDate;

public class ReportController {
    private int reportCounter = 1;

    public Report generateDailyReport() {
        return new Report(reportCounter++, "DAILY", LocalDate.now(), "Daily system usage summary...");
    }

    public Report generateWeeklyReport() {
        return new Report(reportCounter++, "WEEKLY", LocalDate.now(), "Weekly volunteer activity summary...");
    }

    public Report generateMonthlyReport() {
        return new Report(reportCounter++, "MONTHLY", LocalDate.now(), "Monthly performance overview...");
    }
}
