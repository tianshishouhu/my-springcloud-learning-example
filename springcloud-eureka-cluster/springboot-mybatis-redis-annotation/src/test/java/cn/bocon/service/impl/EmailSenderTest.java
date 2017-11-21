package cn.bocon.service.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EmailSenderTest {
	private Logger logger = LogManager.getLogger(getClass());
	@Autowired
	private EmailSender emailSender;

	@Test
	public void sender() {
		String to = "17092087890@126.com";
		String subject = "Test subject";
		String content = "Hello Spring boot Email.";
		
		boolean result = emailSender.sender(to, subject, content);
		logger.info("-------------======---------------"+result);
	}
}