package com.github.lupo112.service;

import io.prometheus.client.Summary;
import io.prometheus.client.spring.web.PrometheusTimeMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IssueService {

	private Summary requestLatency = Summary.build()
		.quantile(0.5, 0.05) // Add 50th percentile (= median) with 5% tolerated error
		.quantile(0.9, 0.01) // Add 90th percentile with 1% tolerated error
		.name("service_computation_latency_seconds")
		.help("service_computation_latency_seconds")
		.register();

	@PrometheusTimeMethod(name = "service_latency_seconds", help = "service_latency_seconds")
	public String list() {
		requestLatency.time(() -> log.info("Doing something..."));
		return "OK";
	}

}
