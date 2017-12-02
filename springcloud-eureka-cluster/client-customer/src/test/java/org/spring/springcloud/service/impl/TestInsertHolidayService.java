package org.spring.springcloud.service.impl;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.springcloud.service.IInsertHolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
