package com.my.project.my.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.my.project.my.domain.MyTest;
import com.my.project.my.service.IMyTestService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 测试Controller
 * 
 * @author ruoyi
 * @date 2024-07-30
 */
@RestController
@RequestMapping("/my/test")
public class MyTestController extends BaseController
{
    @Autowired
    private IMyTestService myTestService;

    /**
     * 查询测试列表
     */
    @PreAuthorize("@ss.hasPermi('my:test:list')")
    @GetMapping("/list")
    public TableDataInfo list(MyTest myTest)
    {
        startPage();
        List<MyTest> list = myTestService.selectMyTestList(myTest);
        return getDataTable(list);
    }

    /**
     * 导出测试列表
     */
    @PreAuthorize("@ss.hasPermi('my:test:export')")
    @Log(title = "测试", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MyTest myTest)
    {
        List<MyTest> list = myTestService.selectMyTestList(myTest);
        ExcelUtil<MyTest> util = new ExcelUtil<MyTest>(MyTest.class);
        util.exportExcel(response, list, "测试数据");
    }

    /**
     * 获取测试详细信息
     */
    @PreAuthorize("@ss.hasPermi('my:test:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(myTestService.selectMyTestById(id));
    }

    /**
     * 新增测试
     */
    @PreAuthorize("@ss.hasPermi('my:test:add')")
    @Log(title = "测试", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MyTest myTest)
    {
        return toAjax(myTestService.insertMyTest(myTest));
    }

    /**
     * 修改测试
     */
    @PreAuthorize("@ss.hasPermi('my:test:edit')")
    @Log(title = "测试", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MyTest myTest)
    {
        return toAjax(myTestService.updateMyTest(myTest));
    }

    /**
     * 删除测试
     */
    @PreAuthorize("@ss.hasPermi('my:test:remove')")
    @Log(title = "测试", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(myTestService.deleteMyTestByIds(ids));
    }
}
