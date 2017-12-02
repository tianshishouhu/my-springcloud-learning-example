package org.spring.springcloud.web;

import java.util.Map;

import org.spring.springcloud.entity.BusAllHoliday;
import org.spring.springcloud.service.IBusAllHolidayService;
import org.spring.springcloud.service.impl.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.collect.Maps;

@RestController("/holidayService")
public class CalendarController {
	@Autowired
	private IBusAllHolidayService busAllHolidayService;
	@Autowired
	private EmailSender emailSender;

    @RequestMapping(value = "/isHoliday/{calendarStr}" ,method = RequestMethod.GET)
    public Map<String, Object> isHoliday(@PathVariable("calendarStr") String calendarStr) {
    	int count = 0;
    	Map<String, Object> resultMap = Maps.newHashMap();
    	Wrapper<BusAllHoliday> wrapper = new EntityWrapper<>();
    	wrapper.eq("holiday_date", calendarStr);
    	try {
			count = busAllHolidayService.selectCount(wrapper);
			resultMap.put("ret", 0); 
			if (count > 0) {
				resultMap.put("isHoliday", "true"); 
			} else {
				resultMap.put("isHoliday", "false"); 
			}
			resultMap.put("msg", "调用成功");
		} catch (Exception e) {
			resultMap.put("ret", 1);
			resultMap.put("msg", "调用异常");
			String to = "17092087890@126.com";
			String subject = "服务异常";
			String content = "是否是工作日服务调用异常" + e.getMessage();
			
			emailSender.sender(to, subject, content);			
			e.printStackTrace();
		}
        return resultMap;
    }
}
