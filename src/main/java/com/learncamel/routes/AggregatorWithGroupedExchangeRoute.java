package com.learncamel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.aggregate.GroupedExchangeAggregationStrategy;

public class AggregatorWithGroupedExchangeRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
			from("direct:grpAggregator")
			.log("Recevied message is ${body} and key $(header.aggregatorId}")
			.aggregate(header("aggregatorId"), new GroupedExchangeAggregationStrategy()).completionSize(3)
            .log("Aggregated Message is ${body} and key ${header.aggregatorId}")
            .to("mock:grpoutput");
	}

}
