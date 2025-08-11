package student;

public interface StudentService {

    StudentResult createStudent(String id, String name);

    StudentResult removeStudent(String id);

}
