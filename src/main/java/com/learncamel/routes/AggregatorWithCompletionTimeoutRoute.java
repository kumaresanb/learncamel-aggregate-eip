package com.learncamel.routes;

import org.apache.camel.builder.RouteBuilder;

import com.learncamel.aggregator.AggregatorSimpleRouteStrategy;

public class AggregatorWithCompletionTimeoutRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
			from("direct:simpleAggregator")
		    .log("Receied Message is ${body} and key ${header.aggregatorId}")
			.aggregate(header("aggregatorId"),new AggregatorSimpleRouteStrategy()).completionSize(2).completionTimeout(3000)
			.log("Aggregated Message is ${body}")
            .to("mock:output");
	}

}
