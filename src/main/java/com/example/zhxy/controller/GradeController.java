package com.example.zhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.zhxy.config.Result;
import com.example.zhxy.pojo.Grade;
import com.example.zhxy.service.GradeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.List;


/**
 * @author JlX
 * @create 2022-04-18 11:04
 */
@Api(tags = "年级控制器")
@RestController
@RequestMapping("/sms/gradeController")
public class GradeController {
    @Autowired
    private GradeService gradeService;


    @ApiOperation("获取全部年级")
    @GetMapping("/getGrades")
    public Result getGrades(){
        List<Grade> grades =gradeService.getGrades();
        return Result.ok(grades);
    }

    //sms/gradeController/deleteGrade
    @DeleteMapping("/deleteGrade")
    @ApiOperation("删除Grade信息")
    public Result deleteGrade(@ApiParam("要删除的所有的grade的id的JSON集合")
            @RequestBody List<Integer> ids){
        gradeService.removeByIds(ids);//mybatisplus 都帮助我们实现了
        return Result.ok();

    }
    @ApiOperation("新增或修改grade,有id属性是修改,没有则是增加")
    @PostMapping("/saveOrUpdateGrade")
    public Result saveOrUpdateGrade(@ApiParam("JSON的Grade对象")
           @RequestBody Grade grade  //加上 @RequestBody 可以将前端传过来的参数封装为一个对象
    ){
        // 接收参数
        // 调用服务层方法完成增减或者修改
        gradeService.saveOrUpdate(grade);//Iservice实现了这个方法
        return Result.ok();
    }


    //sms/gradeController/getGrades/1/3?gradeName=%E4%B8%89

    @ApiOperation("根据年级名称模糊查询,带分页")

    @GetMapping("/getGrades/{pageNo}/{pageSize}")  //@PathVariable("pageNo")Integer pageNo 将restful中的参数赋值给形方法参数
    public Result getGrades(@ApiParam("分页查询的页码数") @PathVariable("pageNo") Integer pageNo,
                            @ApiParam("分页查询的页大小")   @PathVariable("pageSize") Integer pageSize,
                               //@RequestParam("gradeName") 一致的话可以省略
                            @ApiParam("分页查询模糊匹配的名称")  @RequestParam("gradeName") String gradeName) {//直接取参数栏中的参数
        // 分页 带条件查询
        Page<Grade> page =new Page<>(pageNo,pageSize);
        // 通过服务层    gradeName :条件，可以为空
        IPage<Grade> pageRs = gradeService.getGradeByOpr(page,gradeName);
        // 封装Result对象并返回
        return Result.ok(pageRs);
    }
}
