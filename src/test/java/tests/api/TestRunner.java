package tests.api;

import reusable.FunctionalTest;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRunner extends FunctionalTest {

    @Test
    public void apiTest() {
        boolean is_enabled = Boolean.parseBoolean(System.getProperty("default_report"));

        Results results = Runner.path("classpath:tests/api")
                .outputCucumberJson(true)
                .backupReportDir(false)
                .outputHtmlReport(is_enabled)
                .parallel(1);

        generateReport(results.getReportDir(),"Api-Test");

        assertEquals(0, results.getFailCount(), results.getErrorMessages());
    }
}
