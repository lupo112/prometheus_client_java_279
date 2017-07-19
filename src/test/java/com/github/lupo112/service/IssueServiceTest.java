package com.github.lupo112.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IssueService.class)
public class IssueServiceTest {

	@Autowired
	private IssueService issueService;

	@Test
	public void list() throws Exception {
		assertEquals("OK", issueService.list());
	}

}
