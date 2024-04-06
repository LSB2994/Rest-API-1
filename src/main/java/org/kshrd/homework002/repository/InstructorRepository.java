package org.kshrd.homework002.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.homework002.model.entity.Instructor;
import org.kshrd.homework002.model.request.InstructorRequest;

import java.util.List;

@Mapper
public interface InstructorRepository {
    @Select("SELECT * FROM instructors "+
            "LIMIT #{size} "+
            "OFFSET ${size} * (#{page}-1)"
    )
    @Results(
            id = "mapper",
            value = {
                    @Result(property = "instructorId",column = "instructor_id"),
                    @Result(property = "instructorName",column = "instructor_name"),
                    @Result(property = "instructEmail",column = "email")
            }
    )
    List<Instructor> getInstructor(Integer page,Integer size);
    @Select("SELECT * FROM instructors where instructor_id = #{instructorId}")
    @ResultMap("mapper")
    Instructor getInstructorById(Integer instructorId);

    //delete instructor by id
    @Delete("DELETE FROM instructors WHERE instructor_id = #{instructorId}")
    @ResultMap("mapper")
    boolean deleteInstructorId(Integer instructorId);

    //post instructor
    @Select("INSERT INTO instructors(instructor_name,email) "+
            "VALUES (#{rq.instructorName},#{rq.email}) "+"RETURNING instructor_id")
    Integer saveInstructor(@Param("rq") InstructorRequest instructorRequest);

    //update instructor
    @Select("UPDATE instructors" +
            " SET instructor_name = #{rq.instructorName}, email = #{rq.email} WHERE instructor_id = #{instructorId} "+
            "RETURNING instructor_id"
    )
    Integer updateInstructorById(@Param("rq") InstructorRequest instructorRequest,Integer instructorId);
}

