package com.learncamel.routes.aggregate.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import com.learncamel.routes.AggregatorSimpleRoute;

/**
 * Unit test for simple App.
 */
public class AggregatorSimpleRouteTest extends CamelTestSupport {
	
	@Override
	protected RoutesBuilder createRouteBuilder() throws Exception {
		return new AggregatorSimpleRoute();
	}
	
	@Test
	public void aggregateSimpleTest() throws InterruptedException {
		MockEndpoint mock =getMockEndpoint("mock:output");
		
		mock.expectedBodiesReceived("123");
		
		template.sendBodyAndHeader("direct:simpleAggregator", "1", "aggregatorId",1);
		template.sendBodyAndHeader("direct:simpleAggregator", "2", "aggregatorId",1);
		template.sendBodyAndHeader("direct:simpleAggregator", "4", "aggregatorId",2);
		template.sendBodyAndHeader("direct:simpleAggregator", "3", "aggregatorId",1);
		
		assertMockEndpointsSatisfied();

	}
	
	@Test
	public void aggregateMultipleMessages() throws InterruptedException {
		MockEndpoint mock =getMockEndpoint("mock:output");
		
		List<String> expectedValueList=new ArrayList<String>();
		expectedValueList.add("123");
		expectedValueList.add("567");
		
		mock.expectedBodiesReceived(expectedValueList);
		
		
		template.sendBodyAndHeader("direct:simpleAggregator", "1", "aggregatorId",1);
		template.sendBodyAndHeader("direct:simpleAggregator", "2", "aggregatorId",1);
		template.sendBodyAndHeader("direct:simpleAggregator", "4", "aggregatorId",2);
		template.sendBodyAndHeader("direct:simpleAggregator", "3", "aggregatorId",1);
		template.sendBodyAndHeader("direct:simpleAggregator", "5", "aggregatorId",1);
		template.sendBodyAndHeader("direct:simpleAggregator", "6", "aggregatorId",1);
		template.sendBodyAndHeader("direct:simpleAggregator", "7", "aggregatorId",1);
		
		assertMockEndpointsSatisfied();

	}
	
	@Test
	public void aggregateMultipleMessages_DifferentAggregateid() throws InterruptedException {
		MockEndpoint mock =getMockEndpoint("mock:output");
		
		List<String> expectedValueList=new ArrayList<String>();
		expectedValueList.add("123");
		expectedValueList.add("555");
		
		mock.expectedBodiesReceived(expectedValueList);
		
		
		template.sendBodyAndHeader("direct:simpleAggregator", "1", "aggregatorId",1);
		template.sendBodyAndHeader("direct:simpleAggregator", "2", "aggregatorId",1);
		template.sendBodyAndHeader("direct:simpleAggregator", "4", "aggregatorId",2);
		template.sendBodyAndHeader("direct:simpleAggregator", "3", "aggregatorId",1);
		template.sendBodyAndHeader("direct:simpleAggregator", "5", "aggregatorId",1);
		template.sendBodyAndHeader("direct:simpleAggregator", "5", "aggregatorId",1);
		template.sendBodyAndHeader("direct:simpleAggregator", "5", "aggregatorId",1);
		
		assertMockEndpointsSatisfied();

	}
}
