@Test public void testProtocolStackRemoveRemoveSequence() throws Exception {
  KernelServices services=buildKernelServices();
  ModelNode operation=Operations.createCompositeOperation(addStackOp,addTransportOp,addProtocolOp);
  ModelNode result=services.executeOperation(operation);
  Assert.assertEquals(SUCCESS,result.get(OUTCOME).asString());
  result=services.executeOperation(removeStackOp);
  Assert.assertEquals(SUCCESS,result.get(OUTCOME).asString());
  result=services.executeOperation(removeStackOp);
  Assert.assertEquals(FAILED,result.get(OUTCOME).asString());
}
