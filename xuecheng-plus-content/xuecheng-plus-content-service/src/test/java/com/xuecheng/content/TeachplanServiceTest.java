package com.xuecheng.content;

import com.xuecheng.content.service.TeachplanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author bless
 * @Version 1.0
 * @Description
 * @Date 2024-02-27 21:35
 */
@SpringBootTest
public class TeachplanServiceTest {
    @Autowired
    TeachplanService teachplanService;
    @Test
    public void testDeleteTeachplan() {
        teachplanService.deleteTeachplan(305L);
    }
    @Test
    public void testMoveupTeachplan() {
        teachplanService.moveTeachplan("moveup",291L);
    }
}
