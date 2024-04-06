package org.kshrd.homework002.repository;
import org.apache.ibatis.annotations.*;
import org.kshrd.homework002.model.entity.Student;
import org.kshrd.homework002.model.request.StudentRequest;
import java.util.List;

@Mapper
public interface StudentRepository {

    //get students
    @Select("SELECT * FROM students "+
            "LIMIT #{size} "+
            "OFFSET ${size} * (#{page}-1)"
    )
    @Results(
            id = "mapper",
            value = {
                    @Result(property = "studentId",column = "student_id"),
                    @Result(property = "studentName",column = "student_name"),
                    @Result(property = "studentEmail",column = "email"),
                    @Result(property = "phoneNumber",column = "phone_number"),

            }
    )
    @Result(property = "course", column = "student_id",
            many = @Many(select = "org.kshrd.homework002.repository.CourseRepository.getCourseById")
    )
    List<Student> getStudent(Integer page,Integer size);


    //get student by id
    @Select("SELECT * FROM students WHERE student_id = #{studentId}")
    @ResultMap("mapper")
    Student getStudentById(Integer studentId);

    @Select("INSERT INTO students(student_name, email, phone_number) "+
            "VALUES (#{rq.studentName},#{rq.email},#{rq.phoneNumber}) "+
            "RETURNING student_id"
    )
    Integer saveStudent(@Param("rq") StudentRequest studentRequest);

    @Select("INSERT INTO student_course(student_id, course_id) "+
            "VALUES (#{studentId},#{courseId})"
    )
    Integer saveCourseStudentId(Integer studentId, Integer courseId);

    //update student
    @Select("UPDATE students" +
            " SET student_name = #{rq.studentName}, email = #{rq.email}, phone_number = #{rq.phoneNumber} WHERE student_id = #{studentId} "+
            "RETURNING student_id"
    )
    Integer updateStudent(@Param("rq") StudentRequest studentRequest,Integer studentId);

    //delete student
    @Delete("DELETE FROM students WHERE student_id = #{studentId}")
    @ResultMap("mapper")
    boolean deleteStudent(Integer studentId);
}
