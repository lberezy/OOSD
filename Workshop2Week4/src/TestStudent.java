
public class TestStudent {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		final int subjectcount = 3;
		Student student1, student2, student3;
		Subject[] subjects1;
	
		subjects1 = new Subject[subjectcount];
		
		
		subjects1[0] = new Subject("Enterprise Java Programma - Enterprise CodeMonkey Edition",100);
		subjects1[1] = new Subject("Foundations of Cufflink Furnishing",99);
		subjects1[2] = new Subject("Foundations of Disregarding Advice for Experts",98);
		
		student1 = new Student("Chris","123ABC",3);
		student2 = new Student("Stewart","431HCN",3);
		student3 = new Student("Frank","BALLS",3);
		
		student1.setSubjects(subjects1);
		student2.setSubjects(subjects1);
		
		System.out.println("Average marks: " + (student1.getAverageMark() + student2.getAverageMark())/2);
		System.out.println("Number of students: " + student1.getStudentCount());
		System.out.println(student1.toString());
		System.out.println(student1.equals(student1));
		System.out.println(student1.equals(student2));
	}

}
