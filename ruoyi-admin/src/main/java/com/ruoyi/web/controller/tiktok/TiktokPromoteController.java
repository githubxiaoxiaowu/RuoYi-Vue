package com.ruoyi.web.controller.tiktok;

import com.alibaba.druid.support.json.JSONUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.code.domain.TiktokMarketingRecord;
import com.ruoyi.code.enums.AuditStatus;
import com.ruoyi.code.service.ITiktokMarketingRecordService;
import com.ruoyi.code.util.BeanUtil;
import com.ruoyi.code.util.JSONUtil;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.PageUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.web.controller.tiktok.bean.TiktokMarketingRecordAddBean;
import com.ruoyi.web.controller.tiktok.bean.TiktokMarketingRecordQueryBean;
import com.ruoyi.web.controller.tiktok.bean.TiktokMarketingRecordUpdateBean;
import com.ruoyi.web.controller.tiktok.bean.support.WebTiktokMarketingRecordQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * @program: ruoyi
 * @description:
 * @author: zhendong.wu
 * @create: 2022-03-09 17:36
 **/
@Api(value="抖音推广管理",tags = {"抖音推广管理"})
@RestController
@RequestMapping("/tiktok/promote")
public class TiktokPromoteController extends BaseController {
    @Resource
    private ITiktokMarketingRecordService tiktokMarketingRecordService;

    @PreAuthorize("@ss.hasPermi('tiktok:promoterecord:add')")
    @Log(title = "抖音营销记录", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation(value="新增抖音营销记录",response = AjaxResult.class)
    public AjaxResult add(@RequestBody @Validated TiktokMarketingRecordAddBean tiktokMarketingRecordAddBean)
    {
        TiktokMarketingRecord tiktokMarketingRecord= JSONUtil.toJsonToObject(tiktokMarketingRecordAddBean,TiktokMarketingRecord.class);
        tiktokMarketingRecord.apply();
        return toAjax(tiktokMarketingRecordService.save(tiktokMarketingRecord));
    }

    @PreAuthorize("@ss.hasPermi('tiktok:promoterecord:edit')")
    @Log(title = "抖音营销记录", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation(value="修改抖音营销记录",response = AjaxResult.class)
    public AjaxResult edit(@RequestBody @Validated  TiktokMarketingRecordUpdateBean tiktokMarketingRecordUpdateBean)
    {
        TiktokMarketingRecord tiktokMarketingRecordDB= tiktokMarketingRecordService.getById(tiktokMarketingRecordUpdateBean.getId());
        if(tiktokMarketingRecordDB==null){
            return AjaxResult.error(String.format("记录不存在[%s]",tiktokMarketingRecordUpdateBean.getId()));
        }
        if(!AuditStatus.IN_REVIEW.name().equals(tiktokMarketingRecordDB.getAuditStatus())){
            return AjaxResult.error(String.format("状态为审核中可进行修改[%s]",tiktokMarketingRecordUpdateBean.getId()));

        }
        TiktokMarketingRecord tiktokMarketingRecord= JSONUtil.toJsonToObject(tiktokMarketingRecordUpdateBean,TiktokMarketingRecord.class);
        BeanUtil.copyPropertiesIgnoreNull(tiktokMarketingRecord,tiktokMarketingRecordDB);
        return toAjax(tiktokMarketingRecordService.updateById(tiktokMarketingRecordDB));
    }
    /**
     * 获取抖音营销记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('tiktok:promoterecord:query')")
    @GetMapping(value = "/{id}")
    @ApiOperation(value="获取抖音营销记录详细信息",response = TiktokMarketingRecord.class)
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(tiktokMarketingRecordService.getById(id));
    }

    /**
     * 审核状态操作
     */
    @PreAuthorize("@ss.hasPermi('tiktok:promoterecord:audit')")
    @PutMapping(value = "/{id}/{isPass}")
    @ApiOperation(value = "审核状态操作",response = AjaxResult.class)
    public AjaxResult audit(@PathVariable("id") Long id,@PathVariable("isPass") Boolean isPass)
    {
        TiktokMarketingRecord tiktokMarketingRecordDB= tiktokMarketingRecordService.getById(id);
        if(isPass){
            tiktokMarketingRecordDB.pass();
        }else{
            tiktokMarketingRecordDB.refuse();
        }
        return AjaxResult.success(tiktokMarketingRecordService.updateById(tiktokMarketingRecordDB));
    }
    /**
     * 查询抖音营销记录列表
     */
    @PreAuthorize("@ss.hasPermi('tiktok:promoterecord:list')")
    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query ",name="pageNum",dataType="String",required=false,value="pageNum",defaultValue="1"),
            @ApiImplicitParam(paramType="query ",name="pageSize",dataType="String",required=false,value="pageSize",defaultValue="10"),
    })
    @ApiOperation("查询抖音营销记录列表")
    public TableDataInfo list(TiktokMarketingRecordQueryBean tiktokMarketingRecordQueryBean)
    {
        IPage page=PageUtils.getPage();
        IPage<TiktokMarketingRecord> list = tiktokMarketingRecordService.list(page,new WebTiktokMarketingRecordQuery(tiktokMarketingRecordQueryBean));
        return getDataTable(list);
    }

    /**
     * 导出抖音营销记录列表
     */
    @PreAuthorize("@ss.hasPermi('tiktok:promoterecord:export')")
    @Log(title = "抖音营销记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TiktokMarketingRecordQueryBean tiktokMarketingRecordQueryBean)
    {
        IPage<TiktokMarketingRecord> list = tiktokMarketingRecordService.list(new Page(1,Integer.MAX_VALUE),new WebTiktokMarketingRecordQuery(tiktokMarketingRecordQueryBean));
        ExcelUtil<TiktokMarketingRecord> util = new ExcelUtil<TiktokMarketingRecord>(TiktokMarketingRecord.class);
        util.exportExcel(response, list.getRecords(), "抖音营销记录数据");
    }
}
