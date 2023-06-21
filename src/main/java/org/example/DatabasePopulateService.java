package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DatabasePopulateService {

    private static  PreparedStatement insertSt;
    public static final String POPULATE_Worker = "PopulateInsert/Worker.txt";
    public static final String POPULATE_CLIENT = "PopulateInsert/Client.txt";
    public static final String POPULATE_PROJECT = "PopulateInsert/PopulateProject.txt";
    public static final String POPULATE_PROJECT_WORKER = "PopulateInsert/ProjectWorker.txt";

    Connection conn = Database.getInstance().getConnection();


        private  List<Worker> createWorker() throws SQLException {
            List<Worker> workerList = new ArrayList<>();
            try {
                String file = Files.readString(Path.of(POPULATE_Worker));
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String res = scanner.nextLine();
                    Worker worker = new Worker();
                    String[] value = res.split(", ");
                    worker.setName(value[0]);
                    worker.setBirthday(value[1]);
                    worker.setLevel(value[2]);
                    worker.setSalary(Integer.parseInt(value[3]));
                    workerList.add(worker);
                }

                insertSt = conn.prepareStatement("INSERT INTO worker(name, birthday, level, salary) VALUES (?, ?, ?, ?)"
                );

                for (Worker worker : workerList) {
                    insertSt.setString(1, worker.getName());
                    insertSt.setString(2, worker.getBirthday());
                    insertSt.setString(3, worker.getLevel());
                    insertSt.setInt(4, worker.getSalary());
                    insertSt.addBatch();
                }
            }catch (SQLException | IOException e){
                throw new RuntimeException(e);
            }
            insertSt.executeBatch();
            return workerList;
            }

    private  List<Client> createClient() throws  SQLException {
        List<Client> clientList = new ArrayList<>();
        try {
            String file = Files.readString(Path.of(POPULATE_CLIENT));
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String res = scanner.nextLine();
                Client client = new Client();
                client.setName(res);
                clientList.add(client);
            }

            insertSt = conn.prepareStatement("INSERT INTO client(name) VALUES (?)"
            );
            for (Client client : clientList) {
                insertSt.setString(1, client.getName());
                insertSt.addBatch();
            }

            insertSt.executeBatch();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return clientList;
    }

    private  List<Project> createProject() throws SQLException, IOException {
            List<Project> projectList = new ArrayList<>();
            try {
                String file = Files.readString(Path.of(POPULATE_PROJECT));
                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine()) {
                    String res = scanner.nextLine();

                    Project project = new Project();
                    String[] value = res.split(",");
                    project.setClient_id(Long.parseLong(value[0]));
                    project.setStart_date(value[1]);
                    project.setFinish_date(value[2]);
                    projectList.add(project);
                }

                insertSt = conn.prepareStatement("INSERT INTO project(client_id, start_date, finish_date) VALUES (?, ?, ?)"
                );
                for (Project project : projectList) {
                    insertSt.setLong(1, project.getClient_id());
                    insertSt.setString(2, project.getStart_date());
                    insertSt.setString(3, project.getFinish_date());
                    insertSt.addBatch();
                }
            }catch (SQLException | IOException e){
                throw new RuntimeException(e);

            }
            insertSt.executeBatch();
            return projectList;
            }


    private  List<ProjectWorker> createProjectWorker() throws SQLException {
        List<ProjectWorker> projectWorkerList = new ArrayList<>();
        try {
            String file = Files.readString(Path.of(POPULATE_PROJECT_WORKER));
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String res = scanner.nextLine();

                ProjectWorker projectWorker = new ProjectWorker();
                String[] value = res.split(",");
                projectWorker.setProject_id(Long.parseLong(value[0]));
                projectWorker.setWorker_id(Long.parseLong(value[1]));
                projectWorkerList.add(projectWorker);
            }

            insertSt = conn.prepareStatement("INSERT INTO project_worker(project_id, worker_id) VALUES (?, ?)"
            );
            for (ProjectWorker projectWorker : projectWorkerList) {
                insertSt.setLong(1, projectWorker.getProject_id());
                insertSt.setLong(2, projectWorker.getWorker_id());
                insertSt.addBatch();
            }
        }catch (SQLException | IOException e){
            throw new RuntimeException(e);
        }
       insertSt.executeBatch();
        return projectWorkerList;
    }



    public static void main(String[] args) throws SQLException, IOException {
        DatabasePopulateService databasePopulateService = new DatabasePopulateService();
        databasePopulateService.createWorker();
        databasePopulateService.createClient();
        databasePopulateService.createProject();
        databasePopulateService.createProjectWorker();
}

}
