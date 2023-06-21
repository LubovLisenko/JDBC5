package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    private Database database;
    private static PreparedStatement selectSt;
    private static final Connection conn = Database.getInstance().getConnection();

    public static final String FIND_LONGEST_PROJECT = "sql/find_longest_project.sql";
    public static final String FIND_MAX_PROJECTS_CLIENT = "sql/find_max_projects_client.sql";
    public static final String FIND_MAX_SALARY_WORKER = "sql/find_max_salary_worker.sql";
    public static final String FIND_YOUNGEST_ELDEST_WORKERS = "sql/find_youngest_eldest_workers.sql";

    public static final String PRINT_PROJECT_PRICES = "sql/print_project_prices.sql";
    public static final String NAME = "name";
    public static final String PROJECT_COUNT = "PROJECT_COUNT";
    public static final String SALARY = "salary";
    public static final String ID = "id";
    public static final String MONTH_COUNT = "month_count";
    public static final String TYPE = "type";
    public static final String BIRTHDAY= "birthday";
    public static final String PRICE = "price";


    private List<FindMaxSalaryWorker> findMaxSalaryWorkers (){
        ArrayList<FindMaxSalaryWorker> findMaxSalaryWorkerList = new ArrayList<>();
        try {
            String sql = Files.readString(Path.of(FIND_MAX_SALARY_WORKER));
            selectSt = conn.prepareStatement(sql);
            ResultSet rs = selectSt.executeQuery();
            while (rs.next()){
                FindMaxSalaryWorker findMaxSalaryWorker = new FindMaxSalaryWorker();
                findMaxSalaryWorker.setName(rs.getNString(NAME));
                findMaxSalaryWorker.setSalary(rs.getInt(SALARY));
                findMaxSalaryWorkerList.add(findMaxSalaryWorker);
            }

        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
        return findMaxSalaryWorkerList;
    }

    private List<MaxProjectCountClient> findMaxProjectsClient() {
        ArrayList <MaxProjectCountClient> maxProjectCountClients = new ArrayList<>();
        try
        {
            String sql = Files.readString(Path.of(FIND_MAX_PROJECTS_CLIENT));
            selectSt = conn.prepareStatement(sql);
            ResultSet rs = selectSt.executeQuery();

            while (rs.next()) {
                MaxProjectCountClient maxProjectCountClient = new MaxProjectCountClient();
                maxProjectCountClient.setName(rs.getString(NAME));
                maxProjectCountClient.setProjectCount(rs.getInt(PROJECT_COUNT));
                maxProjectCountClients.add(maxProjectCountClient);
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);

        }
        return  maxProjectCountClients;
    }

    private ArrayList <FindLongestProject> findLongestProjects (){
        ArrayList <FindLongestProject> findLongestProjectArrayList = new ArrayList<>();
        try
        {
            String sql = Files.readString(Path.of(FIND_LONGEST_PROJECT));
            selectSt = conn.prepareStatement(sql);
            ResultSet rs = selectSt.executeQuery();

            while (rs.next()) {
               FindLongestProject findLongestProject = new FindLongestProject();
               findLongestProject.setId(rs.getLong(ID));
               findLongestProject.setMonth_count(rs.getInt(MONTH_COUNT));
                findLongestProjectArrayList.add(findLongestProject);

            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);

        }
        return  findLongestProjectArrayList;

    }
    private List<FindYoungestEldestWorkers> findYoungestEldestWorkers(){
        ArrayList <FindYoungestEldestWorkers> findYoungestEldestWorkersList = new ArrayList<>();
        try
        {
            String sql = Files.readString(Path.of(FIND_YOUNGEST_ELDEST_WORKERS));
            selectSt = conn.prepareStatement(sql);
            ResultSet rs = selectSt.executeQuery();

            while (rs.next()) {
                FindYoungestEldestWorkers findYoungestEldestWorkers = new FindYoungestEldestWorkers();
                findYoungestEldestWorkers.setType(rs.getString(TYPE));
                findYoungestEldestWorkers.setName(rs.getString(NAME));
                findYoungestEldestWorkers.setBirthday(rs.getString(BIRTHDAY));
                findYoungestEldestWorkersList.add(findYoungestEldestWorkers);

            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);

        }
        return  findYoungestEldestWorkersList;
    }

    private List<PrintProjectPrices> printProjectPrices(){
        ArrayList<PrintProjectPrices> printProjectPricesList =new ArrayList<>();
        try
        {
            String sql = Files.readString(Path.of(PRINT_PROJECT_PRICES));
            selectSt = conn.prepareStatement(sql);
            ResultSet rs = selectSt.executeQuery();

            while (rs.next()) {
                PrintProjectPrices printProjectPrices = new PrintProjectPrices();
                printProjectPrices.setId(rs.getLong(ID));
                printProjectPrices.setPrice(rs.getInt(PRICE));
                printProjectPricesList.add(printProjectPrices);
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);

        }

        return printProjectPricesList;
    }
public static void main(String[] args) {
    DatabaseQueryService databaseQueryService = new DatabaseQueryService();
    System.out.println("databaseQueryService.findMaxSalaryWorkers() = " + databaseQueryService.findMaxSalaryWorkers());
    System.out.println("databaseQueryService.findMaxProjectsClient() = " + databaseQueryService.findMaxProjectsClient());
    System.out.println("databaseQueryService.findLongestProjects() = " + databaseQueryService.findLongestProjects());
    System.out.println("databaseQueryService.findYoungestEldestWorkers() = " + databaseQueryService.findYoungestEldestWorkers());
    System.out.println("databaseQueryService.printProjectPrices() = " + databaseQueryService.printProjectPrices());
}

}
