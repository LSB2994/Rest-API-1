package org.kshrd.homework002.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.homework002.model.entity.Courses;
import org.kshrd.homework002.model.request.CourseRequest;

import java.util.List;

@Mapper
public interface CourseRepository {
    //get courses
    @Select("SELECT * FROM courses "+
            "LIMIT #{size} "+
            "OFFSET ${size} * (#{page}-1)"
    )
    @Results(
            id = "mapper",
            value = {
                    @Result(property = "courseId",column = "course_id"),
                    @Result(property = "courseName",column = "course_name"),
                    @Result(property = "description",column = "description"),
                    @Result(property = "instructor",column = "instructor_id",
                    many = @Many(select = "org.kshrd.homework002.repository.InstructorRepository.getInstructorById")),
            }
    )
    List<Courses> getCourse(Integer page, Integer size);

    //get course by id
    @Select("SELECT * FROM courses WHERE course_id = #{courseId}")
    @ResultMap("mapper")
    Courses getCourseById(Integer courseId);

    @Select("""
            SELECT c.*
            FROM courses c
            INNER JOIN student_course sc ON c.course_id = sc.course_id
            INNER JOIN students s ON s.student_id = sc.student_id
            WHERE s.student_id = #{studentId}
                      
              """)
    List<Courses> getCourseByStudentId(Integer studentId);



    //delete course by id
    @Delete("DELETE FROM courses WHERE course_id = #{courseId}")
    @ResultMap("mapper")
    boolean deleteCourseById(Integer courseId);

    //post course
    @Select("INSERT INTO courses(course_name, description, instructor_id) "+
            "VALUES (#{rq.courseName},#{rq.description},#{rq.instructorId}) "+"RETURNING course_id"
    )
    Integer insertCourse(@Param("rq") CourseRequest courseRequest);

    //update course
    @Select("UPDATE courses" +
            " SET course_name = #{rq.courseName}, description = #{rq.description}, instructor_id = #{rq.instructorId} WHERE course_id = #{courseId} "+
            "RETURNING course_id"
    )
    Integer updateCourse(@Param("rq") CourseRequest courseRequest,Integer courseId);
}
