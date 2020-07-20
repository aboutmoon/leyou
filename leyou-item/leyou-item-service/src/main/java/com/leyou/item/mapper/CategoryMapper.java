package com.leyou.item.mapper;

import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;
import com.leyou.item.pojo.Category;

import java.util.List;

public interface CategoryMapper extends Mapper<Category>, SelectByIdListMapper<Category, Long> {
}
