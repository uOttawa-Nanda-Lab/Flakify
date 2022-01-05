/** 
 * Tests the ability of the /subsystem=jgroups/stack=X:add() operation to correctly process the optional TRANSPORT and PROTOCOLS parameters.
 */
@Test public void testProtocolStackAddRemoveSequenceWithParameters() throws Exception {
  KernelServices services=buildKernelServices();
  ModelNode result=services.executeOperation(addStackOpWithParams);
  Assert.assertEquals(SUCCESS,result.get(OUTCOME).asString());
  result=services.executeOperation(removeStackOp);
  Assert.assertEquals(SUCCESS,result.get(OUTCOME).asString());
  result=services.executeOperation(removeStackOp);
  Assert.assertEquals(FAILED,result.get(OUTCOME).asString());
}
