@Test @InSequence(3) public void testManagementDescriptionMetrics() throws Exception {
  setStatisticsEnabled(true);
  final ModelNode address=new ModelNode();
  address.add(ModelDescriptionConstants.DEPLOYMENT,"ws-example.war");
  address.add(ModelDescriptionConstants.SUBSYSTEM,WSExtension.SUBSYSTEM_NAME);
  address.add("endpoint","*");
  final ModelNode operation=new ModelNode();
  operation.get(ModelDescriptionConstants.OP).set(ModelDescriptionConstants.READ_RESOURCE_OPERATION);
  operation.get(ModelDescriptionConstants.OP_ADDR).set(address);
  operation.get(ModelDescriptionConstants.INCLUDE_RUNTIME).set(true);
  operation.get(ModelDescriptionConstants.RECURSIVE).set(true);
  ModelNode result=managementClient.getControllerClient().execute(operation);
  List<ModelNode> endpoints=DomainTestSupport.validateResponse(result).asList();
  assertThat(endpoints.size() > 0,is(true));
  for (  final ModelNode endpointResult : result.get("result").asList()) {
    final ModelNode endpoint=endpointResult.get("result");
    final URL wsdlUrl=new URL(endpoint.get("wsdl-url").asString());
    String wsdl=HttpRequest.get(wsdlUrl.toExternalForm(),30,TimeUnit.SECONDS);
    assertThat(wsdl,is(notNullValue()));
    checkCountMetric(endpointResult,managementClient.getControllerClient(),"request-count");
    checkCountMetric(endpointResult,managementClient.getControllerClient(),"response-count");
  }
  setStatisticsEnabled(false);
  result=managementClient.getControllerClient().execute(operation);
  endpoints=DomainTestSupport.validateResponse(result).asList();
  for (  final ModelNode endpointResult : endpoints) {
    final ModelNode endpoint=endpointResult.get("result");
    assertThat(endpoint.hasDefined("request-count"),is(true));
    assertThat(endpoint.get("request-count").asString(),is("1"));
  }
}
