package com.example.zhxy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.zhxy.pojo.Admin;
import org.springframework.stereotype.Repository;

/**
 * @author JlX
 * @create 2022-04-18 10:45
 */
@Repository  //方便spring识别，扫描到当前接口，不用写也可以，因为整合了mybatis
public interface AdminMapper extends BaseMapper<Admin> {
}
