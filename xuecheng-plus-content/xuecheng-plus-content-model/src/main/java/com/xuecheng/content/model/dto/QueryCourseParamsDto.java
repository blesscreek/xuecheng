package com.xuecheng.content.model.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @Author bless
 * @Version 1.0
 * @Description 课程查询条件模型
 * @Date 2024-02-13 14:49
 */
@Data
@ToString
public class QueryCourseParamsDto {

    //审核状态
    private String auditStatus;
    //课程名称
    private String courseName;
    //发布状态
    private String publishStatus;

}