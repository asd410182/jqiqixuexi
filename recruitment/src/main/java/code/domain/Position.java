package code.domain;

public class Position {
	private Integer pid;
	private String pname;
	private String pneed;
	private String pcontent;
	private String psalary;
	private boolean pisopen;
	private String plocation;
	private Integer pcid;
	private String pexperience;
	private String pacademic;
	private String preleasetime;


	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPneed() {
		return pneed;
	}

	public void setPneed(String pneed) {
		this.pneed = pneed;
	}

	public String getPcontent() {
		return pcontent;
	}

	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}

	public String getPsalary() {
		return psalary;
	}

	public void setPsalary(String psalary) {
		this.psalary = psalary;
	}

	public boolean isPisopen() {
		return pisopen;
	}

	public void setPisopen(boolean pisopen) {
		this.pisopen = pisopen;
	}

	public String getPlocation() {
		return plocation;
	}

	public void setPlocation(String plocation) {
		this.plocation = plocation;
	}

	public Integer getPcid() {
		return pcid;
	}

	public void setPcid(Integer pcid) {
		this.pcid = pcid;
	}

	public String getPexperience() {
		return pexperience;
	}

	public void setPexperience(String pexperience) {
		this.pexperience = pexperience;
	}

	public String getPacademic() {
		return pacademic;
	}

	public void setPacademic(String pacademic) {
		this.pacademic = pacademic;
	}

	public String getPreleasetime() {
		return preleasetime;
	}

	public void setPreleasetime(String preleasetime) {
		this.preleasetime = preleasetime;
	}

	@Override
	public String toString() {
		return "Position{" +
				"pid=" + pid +
				", pname='" + pname + '\'' +
				", pneed='" + pneed + '\'' +
				", pcontent='" + pcontent + '\'' +
				", psalary='" + psalary + '\'' +
				", pisopen=" + pisopen +
				", plocation='" + plocation + '\'' +
				", pcid=" + pcid +
				", pexperience='" + pexperience + '\'' +
				", pacademic='" + pacademic + '\'' +
				", preleasetime='" + preleasetime + '\'' +
				'}';
	}
}
