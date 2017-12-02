package org.spring.springcloud.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.spring.springcloud.entity.BusAllHoliday;
import org.spring.springcloud.service.IBusAllHolidayService;
import org.spring.springcloud.service.IInsertHolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.collect.Lists;

@Service
public class InsertHolidayServiceImpl implements IInsertHolidayService {
	@Autowired
	private IBusAllHolidayService busAllHolidayService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.wj.service.impl.IInsertHoliBusAllHolidayerivce#insertHoliday()
	 */
	@Override
	public void insertHoliday() throws Exception {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date start = sdf.parse("2017-01-01");// 开始时间
		Date end = sdf.parse("2017-12-31");// 结束时间
		try {
			List<Date> lists = dateSplit(start, end);
			// -------------------插入周末时间---------------
			if (!lists.isEmpty()) {
				for (Date date : lists) {
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);
					if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
							|| cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
						System.out.println("插入日期:" + sdf.format(date) + ",周末");
						BusAllHoliday busAllHoliday = new BusAllHoliday();
						busAllHoliday.setTitle("周末");
						busAllHoliday.setHolidayDate(sdf.format(date));
						busAllHoliday.setCreateTime(new Date());
						busAllHolidayService.insert(busAllHoliday);
					}
				}
			}
			// ---------------插入节假日时间------------------
			List<BusAllHoliday> holiBusAllHoliday = Lists.newArrayList();
			
			holiBusAllHoliday.add(new BusAllHoliday("元旦", "2017-01-01", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("元旦", "2017-01-02", new Date()));
			
			holiBusAllHoliday.add(new BusAllHoliday("春节", "2017-01-27", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("春节", "2017-01-28", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("春节", "2017-01-29", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("春节", "2017-01-30", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("春节", "2017-01-31", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("春节", "2017-02-01", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("春节", "2017-02-02", new Date()));
			
			holiBusAllHoliday.add(new BusAllHoliday("清明节", "2017-04-02", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("清明节", "2017-04-03", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("清明节", "2017-04-04", new Date()));

			holiBusAllHoliday.add(new BusAllHoliday("劳动节", "2017-04-29", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("劳动节", "2017-04-30", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("劳动节", "2017-05-01", new Date()));

			holiBusAllHoliday.add(new BusAllHoliday("端午节", "2017-05-58", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("端午节", "2017-05-59", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("端午节", "2017-05-30", new Date()));
			
			holiBusAllHoliday.add(new BusAllHoliday("中秋节", "2017-10-01", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("中秋节", "2017-10-02", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("中秋节", "2017-10-03", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("国庆节", "2017-10-04", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("国庆节", "2017-10-05", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("国庆节", "2017-10-06", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("国庆节", "2017-10-07", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("国庆节", "2017-10-08", new Date()));

			for (BusAllHoliday day : holiBusAllHoliday) {
				// 跟周末冲突的，不重复插入
				Wrapper<BusAllHoliday> wrapper = new EntityWrapper<>();
				wrapper.eq("holiday_date", day.getHolidayDate());
				int count = busAllHolidayService.selectCount(wrapper);
				if (count == 0) {
					busAllHolidayService.insert(day);
				}
			}
			// -------------- 剔除补班时间(周末需要补班的)---------------------
			List<BusAllHoliday> workBusAllHoliday = new ArrayList<BusAllHoliday>();
			workBusAllHoliday.add(new BusAllHoliday("补班", "2017-01-22", new Date()));
			workBusAllHoliday.add(new BusAllHoliday("补班", "2017-02-04", new Date()));
			workBusAllHoliday.add(new BusAllHoliday("补班", "2017-04-06", new Date()));
			workBusAllHoliday.add(new BusAllHoliday("补班", "2017-05-27", new Date()));
			workBusAllHoliday.add(new BusAllHoliday("补班", "2017-09-30", new Date()));

			for (BusAllHoliday day : workBusAllHoliday) {
				System.out.println("剔除日期：" + day.getHolidayDate() + ","
						+ day.getTitle());
				Wrapper<BusAllHoliday> wrapper = new EntityWrapper<>();
				wrapper.eq("holiday_date", day.getHolidayDate());
				BusAllHoliday preBusAllHoliday = busAllHolidayService
						.selectOne(wrapper);
				if (preBusAllHoliday != null) {
					busAllHolidayService.deleteById(preBusAllHoliday.getId());
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
/*	
	 * (non-Javadoc)
	 * 
	 * @see cn.wj.service.impl.IInsertHoliBusAllHolidayerivce#insertHoliday()
	 
	@Override
	public void insertHoliday() throws Exception {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date start = sdf.parse("2016-01-01");// 开始时间
		Date end = sdf.parse("2016-12-31");// 结束时间
		try {
			List<Date> lists = dateSplit(start, end);
			// -------------------插入周末时间---------------
			if (!lists.isEmpty()) {
				for (Date date : lists) {
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);
					if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
							|| cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
						System.out.println("插入日期:" + sdf.format(date) + ",周末");
						BusAllHoliday busAllHoliday = new BusAllHoliday();
						busAllHoliday.setTitle("周末");
						busAllHoliday.setHolidayDate(sdf.format(date));
						busAllHoliday.setCreateTime(new Date());
						busAllHolidayService.insert(busAllHoliday);
					}
				}
			}
			// ---------------插入节假日时间------------------
			List<BusAllHoliday> holiBusAllHoliday = new ArrayList<BusAllHoliday>();
			
			holiBusAllHoliday.add(new BusAllHoliday("元旦", "2016-01-01", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("元旦", "2016-01-02", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("元旦", "2016-01-03", new Date()));
			
			holiBusAllHoliday.add(new BusAllHoliday("春节", "2016-02-07", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("春节", "2016-02-08", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("春节", "2016-02-09", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("春节", "2016-02-10", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("春节", "2016-02-11", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("春节", "2016-02-12", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("春节", "2016-02-13", new Date()));
			
			holiBusAllHoliday.add(new BusAllHoliday("清明节", "2016-04-02", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("清明节", "2016-04-03", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("清明节", "2016-04-04", new Date()));

			holiBusAllHoliday.add(new BusAllHoliday("劳动节", "2016-04-30", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("劳动节", "2016-05-01", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("劳动节", "2016-05-02", new Date()));

			holiBusAllHoliday.add(new BusAllHoliday("端午节", "2016-06-09", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("端午节", "2016-06-10", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("端午节", "2016-06-11", new Date()));

			holiBusAllHoliday.add(new BusAllHoliday("中秋节", "2016-09-15", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("中秋节", "2016-09-16", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("中秋节", "2016-09-17", new Date()));

			holiBusAllHoliday.add(new BusAllHoliday("国庆节", "2016-10-01", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("国庆节", "2016-10-02", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("国庆节", "2016-10-03", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("国庆节", "2016-10-04", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("国庆节", "2016-10-05", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("国庆节", "2016-10-06", new Date()));
			holiBusAllHoliday.add(new BusAllHoliday("国庆节", "2016-10-07", new Date()));

			for (BusAllHoliday day : holiBusAllHoliday) {
				// 跟周末冲突的，不重复插入
				Wrapper<BusAllHoliday> wrapper = new EntityWrapper<>();
				wrapper.eq("holiday_date", day.getHolidayDate());
				int count = busAllHolidayService.selectCount(wrapper);
				if (count == 0) {
					busAllHolidayService.insert(day);
				}
			}
			// -------------- 剔除补班时间(周末需要补班的)---------------------
			List<BusAllHoliday> workBusAllHoliday = new ArrayList<BusAllHoliday>();
			workBusAllHoliday.add(new BusAllHoliday("补班", "2016-02-06", new Date()));
			workBusAllHoliday.add(new BusAllHoliday("补班", "2016-02-14", new Date()));
			workBusAllHoliday.add(new BusAllHoliday("补班", "2016-05-02", new Date()));
			workBusAllHoliday.add(new BusAllHoliday("补班", "2016-06-12", new Date()));
			workBusAllHoliday.add(new BusAllHoliday("补班", "2016-09-18", new Date()));
			workBusAllHoliday.add(new BusAllHoliday("补班", "2016-10-08", new Date()));
			workBusAllHoliday.add(new BusAllHoliday("补班", "2016-10-09", new Date()));

			for (BusAllHoliday day : workBusAllHoliday) {
				System.out.println("剔除日期：" + day.getHolidayDate() + ","
						+ day.getTitle());
				Wrapper<BusAllHoliday> wrapper = new EntityWrapper<>();
				wrapper.eq("holiday_date", day.getHolidayDate());
				BusAllHoliday preBusAllHoliday = busAllHolidayService
						.selectOne(wrapper);
				if (preBusAllHoliday != null) {
					busAllHolidayService.deleteById(preBusAllHoliday.getId());
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	private List<Date> dateSplit(Date start, Date end) throws Exception {
		if (!start.before(end))
			throw new Exception("开始时间应该在结束时间之后");
		Long spi = end.getTime() - start.getTime();
		Long step = spi / (24 * 60 * 60 * 1000);// 相隔天数

		List<Date> dateList = new ArrayList<Date>();
		dateList.add(end);
		for (int i = 1; i <= step; i++) {
			dateList.add(new Date(dateList.get(i - 1).getTime()
					- (24 * 60 * 60 * 1000)));// 比上一天减一
		}
		return dateList;
	}

}
