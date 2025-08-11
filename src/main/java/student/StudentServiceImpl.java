package student;

import java.util.HashMap;
import java.util.Map;

public class StudentServiceImpl implements StudentService {

    private final Map<String, String> studentMap;

    public StudentServiceImpl(Map<String, String> studentMap) {
        this.studentMap = studentMap;
    }

    @Override
    public StudentResult createStudent(String id, String name) {
        if(id == null){
            throw new IllegalArgumentException("학번은 null일 수 없다.");
        }

        if(id.length() != 8){
            throw new IllegalArgumentException("학번은 8자리여야 한다.");
        }

        if(studentMap.containsKey(id)){
            throw new IllegalArgumentException("이미 존재하는 학번이다.");
        }

        if(name == null){
            throw new IllegalArgumentException("이름은 null일 수 없다.");
        }

        studentMap.put(id, name);
        return new StudentResult(id, name);
    }

    @Override
    public StudentResult removeStudent(String id) {
        if(!studentMap.containsKey(id)){
            throw new IllegalArgumentException("학번이 존재하지 않는다.");
        }

        studentMap.remove(id);
        return new StudentResult(id, studentMap.get(id));
    }
}
