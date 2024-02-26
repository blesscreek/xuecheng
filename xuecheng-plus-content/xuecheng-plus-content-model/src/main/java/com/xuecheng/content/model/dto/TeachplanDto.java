package com.xuecheng.content.model.dto;

import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.model.po.TeachplanMedia;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @Author bless
 * @Version 1.0
 * @Description
 * @Date 2024-02-16 23:43
 */
@Data
@ToString
public class TeachplanDto extends Teachplan {
    //与媒资管理的信息
    private TeachplanMedia teachplanMedia;
    //小章节
    private List<TeachplanDto> teachPlanTreeNodes;
}
