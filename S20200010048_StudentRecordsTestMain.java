
public class S20200010048_StudentRecordsTestMain {
	public static void main(String[] args) {
		Student st[] = new Student[10];
		String first[] = { "chatla", "naruto", "ryuuzaki", "john", "henry", "abhay", "shardul", "satya", "kush",
				"faisal" };
		String last[] = { "nikith", "uzumaki", "ryuuku", "abhraham", "cavell", "ray", "kurdukar", "kiran", "gupta",
				"khan" };
		for (int i = 0; i < st.length; i++) {
			st[i] = new Student(i + 2, first[i], last[i]);
		}
		SimpleSortStudentRecords simple = new SimpleSortStudentRecords();
		for (int i = 0; i < st.length; i++) {
			simple.addStudent(st[i]);
		}
		simple.simpleSort("First");
		System.out.println("After Simple Sorting array..................");
		System.out.println(simple.searchStudentByFname("naruto").toString());
		simple.simpleSort("Last");
		System.out.println(simple.searchStudentByLname("cavell").toString());
		QuickSortStudentRecords quick = new QuickSortStudentRecords();
		for (int i = 0; i < st.length; i++) {
			quick.addStudent(st[i]);
		}
		quick.quickSort("First", 0, quick.listIndex - 1);
		System.out.println("After Quick Sorting array");
		System.out.println(quick.searchStudentByFname("ryuuzaki").toString());
		quick.quickSort("Last", 0, quick.listIndex - 1);
		System.out.println(quick.searchStudentByLname("nikith").toString());
	}
}

class Student {
	private int roll_no;
	private String firstName;
	private String lastName;

	public Student(int roll, String first, String last) {
		this.roll_no = roll;
		this.firstName = first;
		this.lastName = last;
	}

	public String getFirst() {
		return this.firstName;
	}

	public String getLast() {
		return this.lastName;
	}

	public int getRoll() {
		return this.roll_no;
	}

	@Override
	public String toString() {
		return "Student :\nfirstName= " + firstName + ", lastName= " + lastName + ", roll_no= " + roll_no + "\n";
	}
}

abstract class StudentRecords {
	Student studentList[] = new Student[10];
	int listIndex;

	public void addStudent(Student st) {
		this.studentList[(this.listIndex)++] = st;
	}

	abstract Student searchStudentByFname(String Fname);

	abstract Student searchStudentByLname(String Lname);
}

class SimpleSortStudentRecords extends StudentRecords {

	@Override
	Student searchStudentByFname(String Fname) {
		int l = 0, r = super.listIndex - 1;
		while (l <= r) {
			int m = l + (r - l) / 2;
			if (super.studentList[m].getFirst().compareTo(Fname) == 0)
				return super.studentList[m];
			if (super.studentList[m].getFirst().compareTo(Fname) < 0)
				l = m + 1;
			else
				r = m - 1;
		}
		return null;
	}

	@Override
	Student searchStudentByLname(String Lname) {
		int l = 0, r = super.listIndex - 1;
		while (l <= r) {
			int m = l + (r - l) / 2;
			if (super.studentList[m].getLast().compareTo(Lname) == 0)
				return super.studentList[m];
			if (super.studentList[m].getLast().compareTo(Lname) < 0)
				l = m + 1;
			else
				r = m - 1;
		}
		return null;
	}

	void simpleSort(String key) {
		for (int i = 0; i < super.listIndex; i++) {
			for (int j = i + 1; j < super.listIndex; j++) {
				if (comparer(key, super.studentList[i], super.studentList[j]) > 0) {
					Student temp = super.studentList[i];
					super.studentList[i] = super.studentList[j];
					super.studentList[j] = temp;
				}
			}

		}
	}

	private int comparer(String key, Student s1, Student s2) {
		if (key.compareTo("First") == 0) {
			return s1.getFirst().compareTo(s2.getFirst());
		} else {
			return s1.getLast().compareTo(s2.getLast());
		}
	}
}

class QuickSortStudentRecords extends StudentRecords {

	@Override
	Student searchStudentByFname(String Fname) {
		int l = 0, r = super.listIndex - 1;
		while (l <= r) {
			int m = l + (r - l) / 2;
			if (super.studentList[m].getFirst().compareTo(Fname) == 0)
				return super.studentList[m];
			if (super.studentList[m].getFirst().compareTo(Fname) < 0)
				l = m + 1;
			else
				r = m - 1;
		}
		return null;
	}

	@Override
	Student searchStudentByLname(String Lname) {
		int l = 0, r = super.listIndex - 1;
		while (l <= r) {
			int m = l + (r - l) / 2;
			if (super.studentList[m].getLast().compareTo(Lname) == 0)
				return super.studentList[m];
			if (super.studentList[m].getLast().compareTo(Lname) < 0)
				l = m + 1;
			else
				r = m - 1;
		}
		return null;
	}

	public void quickSort(String key, int first, int last) {
		int i = first;
		int j = last;
		if (j - i >= 1) {
			String pivot = super.studentList[i].getFirst();
			while (j > i) {
				while (comparer(key, super.studentList[i], pivot) <= 0 && i < last && j > i) {
					i++;
				}
				while (comparer(key, super.studentList[j], pivot) >= 0 && j > first && j >= i) {
					j--;
				}
				if (j > i)
					swap(i, j);
			}

			swap(first, j);
			quickSort(key, first, j - 1);
			quickSort(key, j + 1, last);
		}
	}

	private int comparer(String key, Student s, String pivot) {
		if (key.compareTo("First") == 0) {
			return s.getFirst().compareTo(pivot);
		} else {
			return s.getLast().compareTo(pivot);
		}
	}

	private void swap(int i, int j) {
		Student temp = super.studentList[i];
		super.studentList[i] = super.studentList[j];
		super.studentList[j] = temp;
	}
}