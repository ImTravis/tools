package com.tools.common.mapper;

import com.tools.common.db.Person;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Mr.Xue.
 * @program: tools
 * @create 2019-03-26 14:36
 * @des 描述：
 */
@Mapper
public interface PersonMapper {

    void insertBatch(List<Person> list);
}
