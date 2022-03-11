package com.ruoyi.code.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.ruoyi.code.domain.BasePlusEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.code.enums.AuditStatus;
import lombok.Data;

/**
 * <p>
 * 网站推广记录
 * </p>
 *
 * @author zhendong.wu
 * @since 2022-03-09
 */
@Data
public class WebsiteMarketingRecord extends BasePlusEntity {

    private static final long serialVersionUID=1L;

    /**
     * 关键词
     */
    private String keyWord;

    /**
     * 审核状态
     */
    private String auditStatus;

    /**
     * 网站url
     */
    private String websiteUrl;

    /**
     * 搜索引擎
     */
    private String searchEngines;

    /**
     * 部门id
     */
    private Long deptId;


    /**
     * 备注
     */
    private String remark;


    public void pass(){
        this.auditStatus=AuditStatus.APPROVED.name();
    }
    public void refuse(){
        this.auditStatus=AuditStatus.AUDIT_REJECTION.name();
    }
    public void apply(){
        this.auditStatus=AuditStatus.IN_REVIEW.name();
    }


}
