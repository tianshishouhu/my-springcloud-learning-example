package org.spring.springcloud.web;

import java.util.Map;

import org.bouncycastle.asn1.icao.ICAOObjectIdentifiers;
import org.spring.springcloud.service.ICalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calendarService")
public class CalendarController {
    @Autowired
    private ICalendarService calendarService;

    @RequestMapping(value = "/isHoliday/{calendarStr}" ,method = RequestMethod.GET)
    public Map<String, Object> isHoliday(@PathVariable("calendarStr") String calendarStr) {
    	Map<String, Object> resultMap = calendarService.isHoliday(calendarStr);
    	System.out.println(resultMap);
    	return resultMap;
    }
}
