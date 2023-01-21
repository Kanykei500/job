package peaksoft.service;

import peaksoft.dao.JobDao;
import peaksoft.dao.JobDaoImpl;
import peaksoft.model.Job;

import java.util.List;

public class JobServiceImpl implements JobService{
    JobDao jobDao=new JobDaoImpl();
    @Override
    public String createJobTable() {
        jobDao.createJobTable();
        return "Successfully created";
    }

    @Override
    public String addJob(Job job) {
        jobDao.addJob(job);
        return "Successfully added";
    }

    @Override
    public Job getJobById(Long jobId) {
      return jobDao.getJobById(jobId);

    }

    @Override
    public List<Job> sortByExperience(String ascOrDesc) {

        return jobDao.sortByExperience(ascOrDesc);
    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {

        return jobDao.getJobByEmployeeId(employeeId);
    }

    @Override
    public String deleteDescriptionColumn() {
        jobDao.deleteDescriptionColumn();
        return "Successfully deleted";
    }
}
