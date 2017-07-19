package com.github.lupo112.controller;

import com.github.lupo112.service.IssueService;
import io.prometheus.client.Summary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("destinationControllerV1")
@RequestMapping("issue")
public class IssueController {

	private Summary requestLatency = Summary.build()
		.quantile(0.5, 0.05) // Add 50th percentile (= median) with 5% tolerated error
		.quantile(0.9, 0.01) // Add 90th percentile with 1% tolerated error
		.name("controller_latency_seconds")
		.help("controller_latency_seconds")
		.register();

	@Autowired
	private IssueService service;

	@RequestMapping(method = RequestMethod.GET, value = "list")
	public String list() {
		long start = System.currentTimeMillis();

		String result = service.list();
		// something interesting to do

		long millis = System.currentTimeMillis() - start;
		requestLatency.observe(millis / 1000.0);

		return result;
	}

}
