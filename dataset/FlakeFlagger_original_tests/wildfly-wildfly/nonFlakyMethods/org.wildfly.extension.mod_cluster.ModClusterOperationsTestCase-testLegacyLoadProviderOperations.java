@Test public void testLegacyLoadProviderOperations() throws Exception {
  KernelServices services=this.buildKernelServices();
  ModelNode op=Util.createRemoveOperation(getLegacyModClusterConfigDynamicLoadProviderAddress());
  ModelNode result=services.executeOperation(op);
  Assert.assertEquals(result.get(FAILURE_DESCRIPTION).asString(),SUCCESS,result.get(OUTCOME).asString());
  op=Util.createAddOperation(getSimpleLoadProviderAddress(PROXY_NAME));
  result=services.executeOperation(op);
  Assert.assertEquals(result.get(FAILURE_DESCRIPTION).asString(),SUCCESS,result.get(OUTCOME).asString());
  int testFactor=66;
  op=Util.createOperation(WRITE_ATTRIBUTE_OPERATION,getProxyAddress(PROXY_NAME));
  op.get(NAME).set(ProxyConfigurationResourceDefinition.DeprecatedAttribute.SIMPLE_LOAD_PROVIDER.getName());
  op.get(VALUE).set(testFactor);
  result=services.executeOperation(op);
  Assert.assertEquals(result.get(FAILURE_DESCRIPTION).asString(),SUCCESS,result.get(OUTCOME).asString());
  op=Operations.createReadAttributeOperation(getSimpleLoadProviderAddress(PROXY_NAME),SimpleLoadProviderResourceDefinition.Attribute.FACTOR);
  result=services.executeOperation(op);
  Assert.assertEquals(result.get(FAILURE_DESCRIPTION).asString(),SUCCESS,result.get(OUTCOME).asString());
  Assert.assertEquals(testFactor,result.get(RESULT).asInt());
  op=Operations.createReadAttributeOperation(getProxyAddress(PROXY_NAME),ProxyConfigurationResourceDefinition.DeprecatedAttribute.SIMPLE_LOAD_PROVIDER);
  result=services.executeOperation(op);
  Assert.assertEquals(result.get(FAILURE_DESCRIPTION).asString(),SUCCESS,result.get(OUTCOME).asString());
  Assert.assertEquals(testFactor,result.get(RESULT).asInt());
}
