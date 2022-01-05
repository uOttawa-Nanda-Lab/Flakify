@Test public void testLegacyConnectorOperations() throws Exception {
  KernelServices services=this.buildKernelServices();
  String testListenerName="default";
  ModelNode op=Operations.createWriteAttributeOperation(getLegacyModClusterConfigAddress(),ProxyConfigurationResourceDefinition.DeprecatedAttribute.CONNECTOR,new ModelNode(testListenerName));
  ModelNode result=services.executeOperation(op);
  Assert.assertEquals(result.get(FAILURE_DESCRIPTION).asString(),SUCCESS,result.get(OUTCOME).asString());
  op=Operations.createReadAttributeOperation(getProxyAddress("default"),ProxyConfigurationResourceDefinition.Attribute.LISTENER);
  result=services.executeOperation(op);
  Assert.assertEquals(result.get(FAILURE_DESCRIPTION).asString(),SUCCESS,result.get(OUTCOME).asString());
  Assert.assertEquals(testListenerName,result.get(RESULT).asString());
}
