package peaksoft.dao;

import peaksoft.config.Configuration;
import peaksoft.model.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDaoImpl implements JobDao{
    private Connection connection;

    public JobDaoImpl(){
        this.connection= Configuration.getConnection();
    }
    @Override
    public void createJobTable() {
        String sql= """
                create table if not exists jobs(
                id serial primary key,
                position varchar (50) not null,
                profession varchar (50) not null,
                description varchar (50) not null,
                experience int);
                """;
        try (Statement statement = connection.createStatement();){
            statement.execute(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void addJob(Job job) {
        String sql = """
                insert into jobs(position,profession,description,experience)
                values(?,?,?,?);
                """;
        try( PreparedStatement preparedStatement =  connection.prepareStatement(sql);) {

            preparedStatement.setString(1, job.getPosition());
            preparedStatement.setString(2, job.getProfession());
            preparedStatement.setString(3, job.getDescription());
            preparedStatement.setInt(4,job.getExperience());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Job getJobById(Long jobId) {
            Job job=new Job();
        String sql= """
                select * from jobs 
                where id=?;
                
                """;
        try( PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1,jobId);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString(2));
                job.setProfession(resultSet.getString(3));
                job.setDescription(resultSet.getString(4));
                job.setExperience(resultSet.getInt(5));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return job;
    }

    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
        List<Job>jobList=new ArrayList<>();
        String sql = """
                """ ;
        String sql1= """
                 select * from jobs order by experience;""";
        String sql2= """
                select * from jobs order by experience desc;
                """;
        if (ascOrDesc.equals("asc")) {
            sql= sql1;
        }else {
            sql=sql2;
        };
        try (Statement statement = connection.createStatement();){

           ResultSet resultSet= statement.executeQuery(sql);
           while (resultSet.next()){
               jobList.add(new Job(resultSet.getLong("id"),
                       resultSet.getString(2),resultSet.getString(3),
                       resultSet.getString(4),resultSet.getInt(5)));
               System.out.println("Successfully sorted");

           }
           return jobList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        Job job = new Job();
        String sql = """
                select * from jobs inner join employees on 
                jobs.id=employees.job_id  where employees.id=?;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {


            preparedStatement.setLong(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString(2));
                job.setProfession(resultSet.getString(3));
                job.setDescription(resultSet.getString(4));
                job.setExperience(resultSet.getInt(5));
                System.out.println("Successfully get");


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return job;
    }

    @Override
    public void deleteDescriptionColumn() {
        String sql= """
               alter table  jobs drop column description;
                """;
        try( Statement statement = connection.createStatement();) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
