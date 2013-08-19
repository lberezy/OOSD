
public class Subject {
	private static String SubjName;
	private static int Grade;
	
	public Subject(String Name, int Grade){
		this.SubjName = Name;
		this.Grade = Grade;
	}
	
	public int getGrade(){
		return this.Grade;
	}
	
	public String toString(){
		return "Subject Name: " + SubjName +
				"\nGrade: " + Grade;
		
	}
	public boolean equals(Object obj){
		if(obj == this){
			return true;
		} else if(obj == null){
			return false;
		} else if(obj.getClass() == this.getClass()){
			Subject subject = (Subject)obj;
			if( (subject.SubjName.equals(this.SubjName)) && (subject.Grade == this.Grade) ){
				return true;
			}
		} return false;
	}
}
