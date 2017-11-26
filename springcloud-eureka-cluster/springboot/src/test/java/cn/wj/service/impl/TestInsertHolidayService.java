package cn.wj.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.wj.service.IInsertHolidayService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestInsertHolidayService {
	@Autowired
	private IInsertHolidayService insertHolidayService;
	
	@Test
	public void testInsertHoliday() {
		try {
			insertHolidayService.insertHoliday();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
