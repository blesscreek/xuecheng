package com.xuecheng.content.service.impl;

import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.service.CourseCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author bless
 * @Version 1.0
 * @Description TODO
 * @Date 2024-02-15 20:09
 */

@Slf4j
@Service
public class CourseCategoryServiceImpl implements CourseCategoryService {
   @Autowired
    CourseCategoryMapper courseCategoryMapper;
    @Override
    public List<CourseCategoryTreeDto> queryTreeNodes(String id) {
        List<CourseCategoryTreeDto> courseCategoryTreeDtos = courseCategoryMapper.selectTreeNodes(id);
        //将list转为map，以备使用，排除根节点
        Map<String, CourseCategoryTreeDto> mapTemp
                = courseCategoryTreeDtos
                .stream()
                .filter(item -> !id.equals(item.getId()))
                .collect(Collectors.toMap(key -> key.getId(), value -> value, (key1, key2) -> key2));
        //最终返回的list
        List<CourseCategoryTreeDto> categoryTreeDtos = new ArrayList<>();
        //依次遍历每个元素，排除根节点
        courseCategoryTreeDtos
                .stream()
                .filter(item->!id.equals(item.getId()))
                .forEach(item->{
                    if (item.getParentid().equals(id)) {
                        categoryTreeDtos.add(item);
                    }
                    //找到当前节点的父节点
                    CourseCategoryTreeDto treeDtoParent = mapTemp.get(item.getParentid());
                    if (treeDtoParent != null) {
                        if (treeDtoParent.getChildrenTreeNodes() == null) {
                            treeDtoParent.setChildrenTreeNodes(new ArrayList<CourseCategoryTreeDto>());
                        }
                        //下边开始往treeDtoParent树形中放子节点
                        treeDtoParent.getChildrenTreeNodes().add(item);
                    }
                });

        return categoryTreeDtos;
    }
}
