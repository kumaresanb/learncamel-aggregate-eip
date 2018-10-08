package com.learncamel.routes.aggregate.test;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import com.learncamel.routes.AggregateWithCompletionPredicateRoute;

public class AggregateWithCompletionPredicateRouteTest extends CamelTestSupport {
	@Override
	protected RoutesBuilder createRouteBuilder() throws Exception {
		return new AggregateWithCompletionPredicateRoute();
	}

	@Test
	public void predicateTest() throws InterruptedException {
		String orderCreate = "12345,samsung-phone,order-created";
		String orderConfirm = "12345,samsung-phone,order-confirm";

		MockEndpoint mock = getMockEndpoint("mock:output");

		mock.expectedBodiesReceived(orderCreate.concat(":").concat(orderConfirm));

		template.sendBodyAndHeader("direct:completionPredicate", orderCreate, "aggregatorId", 12345);
		template.sendBodyAndHeader("direct:completionPredicate", orderConfirm, "aggregatorId", 12345);

		assertMockEndpointsSatisfied();
	}
}
