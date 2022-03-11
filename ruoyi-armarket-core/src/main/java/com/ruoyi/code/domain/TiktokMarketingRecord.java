package com.ruoyi.code.domain;

import com.ruoyi.code.enums.AuditStatus;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;

/**
 * <p>
 * 抖音营销记录
 * </p>
 *
 * @author zhendong.wu
 * @since 2022-03-09
 */
@Data
public class TiktokMarketingRecord extends BasePlusEntity {

    private static final long serialVersionUID=1L;

    /**
     * 抖音连接
     */
    @Excel(name = "抖音连接")
    private String tiktokUrl;

    /**
     * 抖音用户
     */
    @Excel(name = "tiktokUser")
    private String tiktokUser;

    /**
     * 抖音昵称
     */
    @Excel(name = "抖音昵称")
    private String tiktokNickname;

    /**
     * 使用规则
     */
    private String useRule;

    /**
     * 关键词
     */
    @Excel(name = "keyWord")
    private String keyWord;

    /**
     * 审核状态
     */
    @Excel(name = "auditStatus")
    private String auditStatus;


    /*
     * 备注
     */
    @Excel(name = "备注")
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
