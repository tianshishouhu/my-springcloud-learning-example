package cn.wj.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.wj.service.IInsertHolidayService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wj
 * @since 2017-11-26
 */
@RestController
@RequestMapping("/busAllHoliday")
public class BusAllHolidayController {
    @Autowired
    private IInsertHolidayService insertHolidayService;
    
    @RequestMapping(value = "/holiday/{id}")
    public String findOneCity(@PathVariable("id") Long id) {
        return "ok";
    }
}
