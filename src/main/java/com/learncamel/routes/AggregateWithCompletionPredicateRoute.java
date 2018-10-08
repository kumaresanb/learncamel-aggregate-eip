package com.learncamel.routes;

import org.apache.camel.builder.RouteBuilder;

import com.learncamel.aggregator.AggregatorPredicateStrategy;

public class AggregateWithCompletionPredicateRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
			from("direct:completionPredicate")
			.log("Recevied message is ${body} and key ${header.aggregatorId}")
			.aggregate(header("aggregatorId"),new AggregatorPredicateStrategy())
			.log(" Message after Aggregation Strategy is ${body} and key ${header.aggregatorId}")
			.completionPredicate(body().contains("order-confirm")).eagerCheckCompletion()
			.log("Final Message is : ${body} ")
            .to("mock:output");
	}
}
