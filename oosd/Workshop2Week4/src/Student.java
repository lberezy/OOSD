
public class Student {
	private String Name;
	private String ID;
	private Subject[] Subjects;
	private int SubjectCount;
	private static int StudentCount = 0;
	
	public Student(String Name, String ID, int SubjCount){
		this.Name = Name;
		this.ID = ID;
		this.Subjects = new Subject[SubjCount];
		this.SubjectCount = SubjCount;
		StudentCount += 1;
	}
	
	public int getStudentCount(){
		return this.StudentCount;
	}
	
	public String getName(){
		return this.Name;
	}
	
	public void setName(String Name){
		this.Name = Name;
	}
	
	public String getID(){
		return this.ID;
	}
	
	public void setID(String ID){
		this.ID = ID;
	}
	
	public Subject[] getSubjects(){
		return this.Subjects;
	}
	
	public void setSubjects(Subject Subjects[]){
		this.Subjects = Subjects;
	}
	
	public double getAverageMark(){
		double average = 0.0;
		int i;
		for(i=0;i<SubjectCount;i++){
			average += this.Subjects[i].getGrade();
		}
		return average/SubjectCount;
	}
	
	public String toString(){
		return "Name: " + this.Name +
				"\nID: " + this.ID + 
				"\nTaking " + SubjectCount + " subjects like a BAWS.";
	}
}
