package reusable;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;


public class FunctionalTest {
    @BeforeAll
    public static void before() {
        System.setProperty("karate.env", System.getProperty("karate.env"));
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src\\test\\java\\application.properties");
            properties.load(fileInputStream);
            System.setProperty("default_report",properties.getProperty("default_report"));
            fileInputStream.close();
        }
        catch (IOException ex){
           ex.printStackTrace();
        }
    }

    public static void generateReport(String karateOutputPath, String testName) {
        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[]{"json"}, true);
        List<String> jsonPaths = new ArrayList<String>(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
        Configuration config = new Configuration(new File("target"), testName);
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
    }
    @AfterAll
    public static void folderDelete()  {
        try {
            File folderToDelete = new File("target/karate-reports");
            boolean report = Boolean.parseBoolean(System.getProperty("default_report"));
            if(!report){
                if (folderToDelete.exists()) {
                    deleteFolder(folderToDelete);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteFolder(File folder) {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (!deleteFolder(file)) {
                        return false;
                    }
                }
            }
        }
        return folder.delete();
    }
}
