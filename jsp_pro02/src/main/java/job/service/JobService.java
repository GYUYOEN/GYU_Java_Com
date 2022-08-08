package job.service;

import java.util.List;

import job.model.JobDAO;
import job.model.JobDTO;

public class JobService {

	public List<JobDTO> getAll() {
		JobDAO dao = new JobDAO();
		List<JobDTO> datas = dao.selectAll();
		dao.close();
		
		return datas;
	}

}
