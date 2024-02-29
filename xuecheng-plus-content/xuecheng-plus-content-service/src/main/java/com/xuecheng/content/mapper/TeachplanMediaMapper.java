package com.xuecheng.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuecheng.content.model.po.TeachplanMedia;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author itcast
 */
public interface TeachplanMediaMapper extends BaseMapper<TeachplanMedia> {
    public int deleteTeachplanMedia(long teachplanId);

}
