package com.learncamel.routes.aggregate.test;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import com.learncamel.routes.AggregatorWithGroupedExchangeRoute;

public class AggregatorWithGroupedExchangeRouteTest extends CamelTestSupport {

	@Override
	protected RoutesBuilder createRouteBuilder() throws Exception {
		return new AggregatorWithGroupedExchangeRoute();
	}
	
	@Test
	public void groupExchangeTest() throws InterruptedException {
		MockEndpoint mockEndpoint=getMockEndpoint("mock:grpoutput");
		mockEndpoint.expectedMessageCount(1);
		
		template.sendBodyAndHeader("direct:grpAggregator", "1", "aggregatorId", 1);
        template.sendBodyAndHeader("direct:grpAggregator", "2", "aggregatorId", 1);
        template.sendBodyAndHeader("direct:grpAggregator", "3", "aggregatorId", 1);
        
        assertMockEndpointsSatisfied();
        
        Exchange exchangeList = mockEndpoint.getExchanges().get(0);
        
        List<Exchange> exchangeList1 = (List<Exchange>) exchangeList.getProperty(Exchange.GROUPED_EXCHANGE);
        
        for (Exchange exchange : exchangeList1) {
        	 System.out.println("Exchange Body is : " + exchange.getIn().getBody());
		}
	}
}
