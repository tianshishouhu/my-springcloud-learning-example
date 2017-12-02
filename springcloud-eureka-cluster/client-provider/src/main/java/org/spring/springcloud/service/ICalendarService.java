package org.spring.springcloud.service;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("calendar-service")
public interface ICalendarService {
    @RequestMapping(value = "/isHoliday/{calendarStr}" ,method = RequestMethod.GET)
    public Map<String, Object> isHoliday(@PathVariable("calendarStr") String calendarStr);
}
