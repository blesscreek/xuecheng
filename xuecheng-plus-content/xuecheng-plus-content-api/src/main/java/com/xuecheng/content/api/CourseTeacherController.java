package com.xuecheng.content.api;

import com.xuecheng.content.mapper.CourseTeacherMapper;
import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.service.CourseTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author bless
 * @Version 1.0
 * @Description
 * @Date 2024-02-29 21:10
 */
@Api(value = "课程教师编辑接口",tags = "课程教师编辑接口")
@RestController
public class CourseTeacherController {
    @Autowired
    CourseTeacherService courseTeacherService;

    @ApiOperation("查询教师接口")
    @GetMapping("/courseTeacher/list/{courseId}")
    public CourseTeacher getCourseTeacher(@PathVariable Long courseId) {
        CourseTeacher courseTeacher = courseTeacherService.getCourseTeacher(courseId);
        return courseTeacher;
    }



}
