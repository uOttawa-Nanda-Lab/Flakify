@Test public void testProxyOperations() throws Exception {
  KernelServices services=this.buildKernelServices();
  ModelNode op=Util.createOperation(READ_OPERATION_NAMES_OPERATION,getProxyAddress("default"));
  ModelNode result=services.executeOperation(op);
  Assert.assertEquals(result.get(FAILURE_DESCRIPTION).asString(),SUCCESS,result.get(OUTCOME).asString());
  for (  ProxyOperation proxyOperation : ProxyOperation.values()) {
    String operationName=proxyOperation.getDefinition().getName();
    Assert.assertTrue(String.format("'%s' operation is not registered at the proxy address",operationName),result.get(RESULT).asList().contains(new ModelNode(operationName)));
  }
}
