package com.learncamel.aggregator;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class AggregatorSimpleRouteStrategy implements AggregationStrategy {

	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		if(oldExchange==null)
		{
			return newExchange;
		}else {
			String oldBody=(String) oldExchange.getIn().getBody();
			String newBody=(String) newExchange.getIn().getBody();
			
			newBody=oldBody.concat(newBody);
			
			newExchange.getIn().setBody(newBody);
		}
		return newExchange;
	}

}
