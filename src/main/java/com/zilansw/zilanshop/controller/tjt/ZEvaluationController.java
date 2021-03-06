package com.zilansw.zilanshop.controller.tjt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zilansw.zilanshop.commons.MessageBack;
import com.zilansw.zilanshop.commons.PageBean;
import com.zilansw.zilanshop.pojo.ZEvaluation;
import com.zilansw.zilanshop.service.tjt.ZEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tjt
 * @date 2019-06-27
 */
@Controller
@RequestMapping("admin/zEvaluation")
public class ZEvaluationController {

    @Autowired
    private ZEvaluationService zEvaluationService;

    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping("getList")
    @ResponseBody
    public Map<String, Object> selectAll(@RequestParam(defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "5") Integer limit) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        List<ZEvaluation> iPage = zEvaluationService.selectAll(result, ((pageIndex - 1) * limit), limit);
        QueryWrapper<ZEvaluation> queryWrapper = new QueryWrapper<>();
        PageBean pageBean = new PageBean(pageIndex, limit, zEvaluationService.selectCount(queryWrapper));
        map.put("data", iPage);
        map.put("pageBean", pageBean);
        return map;
    }

    /**
     * 新增评论
     *
     * @param zEvaluation
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> insert(ZEvaluation zEvaluation) {
        zEvaluation.setCreateTime(new Date());
        zEvaluationService.insert(zEvaluation);
        return MessageBack.MSG(200, "新增成功");
    }

    /**
     * 修改评论
     *
     * @param zEvaluation
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public Map<String, Object> update(ZEvaluation zEvaluation) {
        zEvaluationService.update(zEvaluation);
        return MessageBack.MSG(200, "修改成功");
    }

    /**
     * 删除评论
     *
     * @param eid
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(Integer eid) {
        zEvaluationService.delete(eid);
        return MessageBack.MSG(200, "删除成功");
    }
}
