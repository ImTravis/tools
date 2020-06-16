package com.tools.common.db;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Xue.
 * @program: tools
 * @create 2019-04-30 14:19
 * @des 描述：
 */
@Data
@Scope(value="singleton")
@Component
@Service
public class Book {

    private String name;

    private String time;
}
