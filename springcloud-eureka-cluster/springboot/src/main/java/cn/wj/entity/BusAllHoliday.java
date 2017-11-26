package cn.wj.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wj
 * @since 2017-11-26
 */
@TableName("bus_all_holiday")
public class BusAllHoliday extends Model<BusAllHoliday> {

    private static final long serialVersionUID = 1L;

	private String id;
	private String title;
	@TableField("holiday_date")
	private String holidayDate;
	@TableField("create_time")
	private Date createTime;
	@TableField("update_time")
	private Date updateTime;

	public BusAllHoliday() {
		super();
	}	
	
	public BusAllHoliday(String title, String holidayDate, Date createTime) {
		super();
		this.title = title;
		this.holidayDate = holidayDate;
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(String holidayDate) {
		this.holidayDate = holidayDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
