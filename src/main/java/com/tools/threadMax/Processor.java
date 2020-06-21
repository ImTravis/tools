package com.tools.threadMax;

import com.tools.common.db.Person;
import com.tools.common.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 处理数据
 */
@Service
public class Processor {
    @Autowired(required = false)
    private PersonMapper personMapper;

    public boolean handler(List<Person> personList){
        try{
            personMapper.insertBatch(personList);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }



    }

}
