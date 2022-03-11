package com.ruoyi.code.domain;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class BasePlusEntity<T> extends Model implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@JsonSerialize(using= ToStringSerializer.class)
	@TableId(value = "id", type = IdType.INPUT)
	private Long id;
	/**
	 * 乐观锁
	 */
	@Version
	@TableField(value="optimistic",fill =  FieldFill.INSERT)
	private Long optimistic ;


	/**
	 * 创建日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField(value="create_time",fill =  FieldFill.INSERT_UPDATE)
	private Date createTime;

	/**
	 * 修改日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField(value="update_time",fill =  FieldFill.UPDATE)
	private Date updateTime;

	/**
	 * 用户id
	 */
	@TableField(value="user_id",fill =  FieldFill.INSERT)
	private Long userId;
}
