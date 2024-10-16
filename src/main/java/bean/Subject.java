package bean;

public class Subject {
	public Subject(String id, String subjectname) {
		super();
		this.id = id;
		this.subjectname = subjectname;
	}
	public Subject() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String id ;
	private String subjectname ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubjectname() {
		return subjectname;
	}
	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}
	@Override
	public String toString() {
		return "Subject [id=" + id + ", subjectname=" + subjectname + "]";
	}
}
