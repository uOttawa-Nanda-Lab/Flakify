@Test public void testLegacyPathOperations() throws Exception {
  KernelServices services=this.buildKernelServices();
  ModelNode op=createLegacyModClusterConfigWriteAttributeOperation(ProxyConfigurationResourceDefinition.Attribute.PING,new ModelNode(10));
  ModelNode result=services.executeOperation(op);
  Assert.assertEquals(result.get(FAILURE_DESCRIPTION).asString(),SUCCESS,result.get(OUTCOME).asString());
  op=createLegacyModClusterConfigLoadMetricWriteAttributeOperation("mem",LoadMetricResourceDefinition.SharedAttribute.WEIGHT,new ModelNode(10));
  result=services.executeOperation(op);
  Assert.assertEquals(result.get(FAILURE_DESCRIPTION).asString(),SUCCESS,result.get(OUTCOME).asString());
}
