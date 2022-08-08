package job.model;

public class JobDTO {
	private String jobId;
	private String jobName;
	private int minSalary;
	private int maxSalary;
	
	public String getJobId() {
		return jobId;
	}
	
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	
	public String getJobName() {
		return jobName;
	}
	
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	public int getMinSalary() {
		return minSalary;
	}
	
	public void setMinSalary(int minSalary) {
		this.minSalary = minSalary;
	}
	
	public int getMaxSalary() {
		return maxSalary;
	}
	
	public void setMaxSalary(int maxSalary) {
		this.maxSalary = maxSalary;
	}
	
	@Override
	public String toString() {
		return "JobDTO [jobId=" + jobId + ", jobName=" + jobName + ", minSalary=" + minSalary + ", maxSalary="
				+ maxSalary + "]";
	}
}
