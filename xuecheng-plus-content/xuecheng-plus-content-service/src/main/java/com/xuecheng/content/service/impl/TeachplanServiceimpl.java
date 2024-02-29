package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.mapper.TeachplanMapper;
import com.xuecheng.content.mapper.TeachplanMediaMapper;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.service.TeachplanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author bless
 * @Version 1.0
 * @Description
 * @Date 2024-02-19 23:52
 */
@Service
public class TeachplanServiceimpl implements TeachplanService {

    @Autowired
    TeachplanMapper teachplanMapper;
    @Autowired
    TeachplanMediaMapper teachplanMediaMapper;

    @Override
    public List<TeachplanDto> findTeachplanTree(Long courseId) {
        List<TeachplanDto> teachplanDtos = teachplanMapper.selectTreeNodes(courseId);
        return teachplanDtos;
    }

    private int getTeachplanCount(Long courseId, Long parentId) {
        LambdaQueryWrapper<Teachplan> qw = new LambdaQueryWrapper<>();
        qw = qw.eq(Teachplan::getCourseId, courseId).eq(Teachplan::getParentid, parentId);
        Integer count = teachplanMapper.selectCount(qw);
        return count + 1;
    }
    @Override
    public void saveTeachplan(SaveTeachplanDto saveTeachplanDto) {
        //通过课程计划id判断是新增和修改
        Long teachplanrId = saveTeachplanDto.getId();
        if (teachplanrId == null) {
            //新增
            Teachplan teachplan = new Teachplan();
            BeanUtils.copyProperties(saveTeachplanDto,teachplan);
            //确定排序字段，找到它的同级节点个数，排序字段就是个数加1 select count(1) from teachplan where course_id=117 and parentid=0
            Long parentid = saveTeachplanDto.getParentid();
            Long courseId = saveTeachplanDto.getCourseId();
            int teachplanCount = getTeachplanCount(courseId, parentid);
            teachplan.setOrderby(teachplanCount);

            teachplanMapper.insert(teachplan);
        } else {
            //修改
            Teachplan teachplan = teachplanMapper.selectById(teachplanrId);
            BeanUtils.copyProperties(saveTeachplanDto,teachplan);
            teachplanMapper.updateById(teachplan);
        }
    }

    @Override
    public void deleteTeachplan(Long teachplanId) {
        int i = teachplanMapper.deleteTeachPlan(teachplanId);
        if (i == 0) {
            XueChengPlusException.cast("课程计划信息还有子级信息，无法操作");
            return;
        }
        int j = teachplanMediaMapper.deleteTeachplanMedia(teachplanId);
        return;
    }

    @Override
    public void moveTeachplan(String moveType, Long teachplanId) {
        Teachplan teachplan = teachplanMapper.selectById(teachplanId);
        if ("moveup".equals(moveType)) {
            if (teachplan.getOrderby() == 1) {
                XueChengPlusException.cast("该课程计划已在最上");
            }
            int i = teachplanMapper.moveupTeachplan1(teachplanId);
            int j = teachplanMapper.moveupTeachplan2(teachplanId);
            if (i == 0 || j == 0) {
                XueChengPlusException.cast("课程计划上移失败");
            }
        } else if ("movedown".equals(moveType)) {
            LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Teachplan::getParentid,teachplan.getParentid())
                    .eq(Teachplan::getGrade, teachplan.getGrade())
                    .eq(Teachplan::getCourseId, teachplan.getCourseId());
            Integer integer = teachplanMapper.selectCount(queryWrapper);
            if (teachplan.getOrderby() == integer) {
                XueChengPlusException.cast("该课程已经在最下");
            }
            int i = teachplanMapper.movedownTeachplan1(teachplanId);
            int j = teachplanMapper.movedownTeachplan2(teachplanId);
            if (i == 0 || j == 0) {
                XueChengPlusException.cast("课程计划下移失败");
            }
        }
        return;
    }


}
