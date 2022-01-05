/** 
 * Tests that legacy proxy operations are registered at the subsystem level.
 */
@Test public void testLegacyProxyOperations() throws Exception {
  KernelServices services=this.buildKernelServices();
  ModelNode op=Util.createOperation(READ_OPERATION_NAMES_OPERATION,getSubsystemAddress());
  ModelNode result=services.executeOperation(op);
  Assert.assertEquals(result.get(FAILURE_DESCRIPTION).asString(),SUCCESS,result.get(OUTCOME).asString());
  for (  ProxyOperation proxyOperation : ProxyOperation.values()) {
    String operationName=proxyOperation.getDefinition().getName();
    Assert.assertTrue(String.format("'%s' legacy operation is not registered",operationName),result.get(RESULT).asList().contains(new ModelNode(operationName)));
    ModelNode rodOp=Util.createOperation(READ_OPERATION_DESCRIPTION_OPERATION,getSubsystemAddress());
    rodOp.get(NAME).set(operationName);
    ModelNode rodResult=services.executeOperation(rodOp);
    Assert.assertEquals(rodResult.get(FAILURE_DESCRIPTION).asString(),SUCCESS,rodResult.get(OUTCOME).asString());
    Assert.assertTrue(rodResult.get(RESULT).hasDefined(DEPRECATED));
  }
}
