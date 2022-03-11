package com.ruoyi.web.controller.website;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.code.domain.TiktokMarketingRecord;
import com.ruoyi.code.domain.WebsiteMarketingRecord;
import com.ruoyi.code.enums.AuditStatus;
import com.ruoyi.code.service.IWebsiteMarketingRecordService;
import com.ruoyi.code.util.BeanUtil;
import com.ruoyi.code.util.JSONUtil;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.PageUtils;
import com.ruoyi.web.controller.tiktok.bean.TiktokMarketingRecordQueryBean;
import com.ruoyi.web.controller.tiktok.bean.support.WebTiktokMarketingRecordQuery;
import com.ruoyi.web.controller.website.bean.WebsiteMarketingRecordAddBean;
import com.ruoyi.web.controller.website.bean.WebsiteMarketingRecordQueryBean;
import com.ruoyi.web.controller.website.bean.WebsiteMarketingRecordUpdateBean;
import com.ruoyi.web.controller.website.bean.support.ControllerWebsiteMarketingRecordQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: ruoyi
 * @description:
 * @author: zhendong.wu
 * @create: 2022-03-09 23:15
 **/
@Api(value="网站推广管理",tags = {"网站推广管理"})
@RestController
@RequestMapping("/website/promote")
public class WebsiteMarketingRecordController extends BaseController {

    @Resource
   private IWebsiteMarketingRecordService websiteMarketingRecordService;

    @PreAuthorize("@ss.hasPermi('website:promoterecord:add')")
    @Log(title = "新增网站推广记录", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation(value="新增网站推广记录",response = AjaxResult.class)
    public AjaxResult add(@RequestBody @Validated WebsiteMarketingRecordAddBean websiteMarketingRecordAddBean)
    {
        WebsiteMarketingRecord websiteMarketingRecord= JSONUtil.toJsonToObject(websiteMarketingRecordAddBean,WebsiteMarketingRecord.class);
        websiteMarketingRecord.apply();
        return toAjax(websiteMarketingRecordService.save(websiteMarketingRecord));
    }
    @PreAuthorize("@ss.hasPermi('website:promoterecord:edit')")
    @Log(title = "修改网站推广记录", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation(value="修改网站推广记录",response = AjaxResult.class)
    public AjaxResult edit(@RequestBody @Validated WebsiteMarketingRecordUpdateBean websiteMarketingRecordUpdateBean)
    {
        WebsiteMarketingRecord db= websiteMarketingRecordService.getById(websiteMarketingRecordUpdateBean.getId());
        if(db==null){
            return AjaxResult.error(String.format("记录不存在[%s]",websiteMarketingRecordUpdateBean.getId()));
        }
        if(!AuditStatus.IN_REVIEW.name().equals(db.getAuditStatus())){
            return AjaxResult.error(String.format("状态为审核中可进行修改[%s]",websiteMarketingRecordUpdateBean.getId()));

        }
        WebsiteMarketingRecord websiteMarketingRecord= JSONUtil.toJsonToObject(websiteMarketingRecordUpdateBean,WebsiteMarketingRecord.class);
        BeanUtil.copyPropertiesIgnoreNull(websiteMarketingRecord,db);
        return toAjax(websiteMarketingRecordService.updateById(db));
    }

    /**
     * 审核状态操作
     */
    @PreAuthorize("@ss.hasPermi('website:promoterecord:audit')")
    @PutMapping(value = "/{id}/{isPass}")
    @ApiOperation(value = "审核状态操作",response = AjaxResult.class)
    public AjaxResult audit(@PathVariable("id") Long id,@PathVariable("isPass") Boolean isPass) {
        return AjaxResult.success(websiteMarketingRecordService.audit(id,isPass));
    }

    /**
     * 查询抖音营销记录列表
     */
    @PreAuthorize("@ss.hasPermi('website:promoterecord:list')")
    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query ",name="pageNum",dataType="String",required=false,value="pageNum",defaultValue="1"),
            @ApiImplicitParam(paramType="query ",name="pageSize",dataType="String",required=false,value="pageSize",defaultValue="10"),
    })
    @ApiOperation("查询网站推广记录列表")
    public TableDataInfo list(WebsiteMarketingRecordQueryBean websiteMarketingRecordQueryBean)
    {
        IPage page=PageUtils.getPage();
        List<WebsiteMarketingRecord> list = websiteMarketingRecordService.list(page,new ControllerWebsiteMarketingRecordQuery(websiteMarketingRecordQueryBean));
        return getDataTable(list);
    }
}
