CREATE DATABASE homework02;

CREATE TABLE courses(
                        course_id SERIAL PRIMARY KEY,
                        course_name VARCHAR(2000),
                        description VARCHAR(2000),
                        instructor_id int,
                        constraint fk_instructor_id foreign key(instructor_id) references instructors(instructor_id)
);
CREATE TABLE student_course(
    student_id int,
    course_id int,
    constraint fk_student_id foreign key (student_id) references students(student_id)
);
SELECT * FROM students;