package com.example.zhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.zhxy.mapper.GradeMapper;
import com.example.zhxy.pojo.Grade;
import com.example.zhxy.service.GradeService;
import freemarker.template.utility.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author JlX
 * @create 2022-04-18 11:01
 */
@Service
@Transactional
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements GradeService {

    @Override
    public IPage<Grade> getGradeByOpr(Page<Grade> pageParam, String gradeName) {
        QueryWrapper<Grade> gradeQueryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(gradeName)) {
            gradeQueryWrapper.like("name",gradeName);
        }
        gradeQueryWrapper.orderByDesc("id");//id降序
        //Page 实现了Ipage接口
        Page<Grade> page = baseMapper.selectPage(pageParam, gradeQueryWrapper);
        return page;
    }

    @Override
    public List<Grade> getGrades() {
        return  baseMapper.selectList(null);
    }
}
