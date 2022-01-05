@Test @InSequence(2) public void testManagementDescription() throws Exception {
  final ModelNode address=new ModelNode();
  address.add(ModelDescriptionConstants.DEPLOYMENT,"ws-example.war");
  address.add(ModelDescriptionConstants.SUBSYSTEM,WSExtension.SUBSYSTEM_NAME);
  address.add("endpoint","*");
  final ModelNode operation=new ModelNode();
  operation.get(ModelDescriptionConstants.OP).set(ModelDescriptionConstants.READ_RESOURCE_OPERATION);
  operation.get(ModelDescriptionConstants.OP_ADDR).set(address);
  operation.get(ModelDescriptionConstants.INCLUDE_RUNTIME).set(true);
  operation.get(ModelDescriptionConstants.RECURSIVE).set(true);
  final ModelNode result=managementClient.getControllerClient().execute(operation);
  List<ModelNode> endpoints=DomainTestSupport.validateResponse(result).asList();
  assertThat(endpoints.size() > 0,is(true));
  for (  final ModelNode endpointResult : result.get("result").asList()) {
    final ModelNode endpoint=endpointResult.get("result");
    assertThat(endpoint.hasDefined("class"),is(true));
    assertThat(endpoint.hasDefined("name"),is(true));
    assertThat(endpoint.hasDefined("wsdl-url"),is(true));
    assertThat(endpoint.get("wsdl-url").asString().endsWith("?wsdl"),is(true));
    assertThat(endpoint.hasDefined("request-count"),is(true));
    assertThat(endpoint.get("request-count").asString(),is("0"));
  }
}
