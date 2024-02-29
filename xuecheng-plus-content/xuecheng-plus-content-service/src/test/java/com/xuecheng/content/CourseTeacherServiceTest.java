package com.xuecheng.content;

import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.service.CourseTeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author bless
 * @Version 1.0
 * @Description
 * @Date 2024-02-29 21:25
 */

@SpringBootTest
public class CourseTeacherServiceTest {
    @Autowired
    CourseTeacherService courseTeacherService;
    @Test
    public void getCourseTeacherTest() {
        CourseTeacher courseTeacher = courseTeacherService.getCourseTeacher(74L);
        System.out.println(courseTeacher);

    }
}
