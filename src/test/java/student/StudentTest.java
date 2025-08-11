package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StudentTest {

    private StudentServiceImpl studentServiceImpl;
    private Map<String, String> studentMap;

    @BeforeEach
    void setUp() {
        studentMap = new HashMap<>();
        studentServiceImpl = new StudentServiceImpl(studentMap);
    }

    @Test
    @DisplayName("학번이 8자리가 아니면 예외를 던져야 한다.")
    void throwsExceptionWhenStudentIdIsNotEightDigits() {
        assertThrows(IllegalArgumentException.class, () -> {
            studentServiceImpl.createStudent("1234567", "오준택");
        });
    }

    @Test
    @DisplayName("학번이 null 값이면 예외를 던져야 한다.")
    void throwsExceptionWhenStudentIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            studentServiceImpl.createStudent(null, "오준택");
        });
    }

    @Test
    @DisplayName("학번이 중복되면 예외를 던져야 한다.")
    void throwsExceptionWhenStudentIdIsAlreadyUsed() {
        // given
        studentServiceImpl.createStudent("20211956", "오준택");

        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            studentServiceImpl.createStudent("20211956", "준택");
        });
    }

    @Test
    @DisplayName("이름이 null 값이면 예외를 던져야 한다.")
    void throwsExceptionWhenStudentNameIsNull() {
        // given
        String id = "20211956";
        String name = null;

        // when, then
        assertThrows(IllegalArgumentException.class, () -> {
            studentServiceImpl.createStudent(id, name);
        });
    }

    @Test
    @DisplayName("잘 추가 되었는지 확인한다.")
    void studentIsAdded() {
        // given
        String id = "20211956";
        String name = "오준택";

        // when
        studentServiceImpl.createStudent(id, name);

        // then
        assertEquals(name, studentMap.get(id));
    }

    @Test
    @DisplayName("잘 삭제 되었는지 확인한다.")
    void studentIsRemoved() {
        // given
        String id = "20211956";
        studentServiceImpl.createStudent("20211956", "오준택");

        // when
        StudentResult removeStudent= studentServiceImpl.removeStudent("20211956");

        // then
        assertEquals(id, removeStudent.id());

    }
}