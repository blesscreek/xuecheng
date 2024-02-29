package com.xuecheng.content;

import com.xuecheng.content.mapper.TeachplanMapper;
import com.xuecheng.content.mapper.TeachplanMediaMapper;
import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.service.CourseCategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author bless
 * @Version 1.0
 * @Description
 * @Date 2024-02-14 15:43
 */

@SpringBootTest
public class TeachplanMapperTests {
    @Autowired
    TeachplanMapper teachplanMapper;
    @Autowired
    TeachplanMediaMapper teachplanMediaMapper;

    @Test
    public void testSelectTreeNodes() {
        List<TeachplanDto> teachplanDtos = teachplanMapper.selectTreeNodes(117L);
        System.out.println(teachplanDtos);
    }

    @Test
    public void testDeleteTeachplan() {
        int i = teachplanMapper.deleteTeachPlan(307L);
        System.out.println(i);
    }
    @Test
    public void testDeleteTeachplanMedia() {
        int i = teachplanMediaMapper.deleteTeachplanMedia(305L);
        System.out.println(i);
    }

    @Test
    public void moveupTeachplan() {
//        int i = teachplanMapper.moveupTeachplan(241L);
//        System.out.println(i);
    }

}
