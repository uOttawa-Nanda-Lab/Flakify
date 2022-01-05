/** 
 * Test for https://issues.jboss.org/browse/WFLY-5290 where server/test hangs when using legacy TRANSPORT alias: Create a simple stack, then remove, re-add a different transport, remove twice expecting the 2nd remove to fail. Tests both situations when stack in inferred from :add operation and when its inferred from the existing resource.
 */
@Test public void testLegacyTransportAliasSequence() throws Exception {
  KernelServices services=buildKernelServices();
  String stackName="legacyStack";
  ModelNode result=services.executeOperation(getProtocolStackAddOperationWithParameters(stackName));
  Assert.assertEquals(SUCCESS,result.get(OUTCOME).asString());
  result=services.executeOperation(getLegacyThreadPoolAddOperation(stackName,"default"));
  Assert.assertEquals(SUCCESS,result.get(OUTCOME).asString());
  ModelNode op=getLegacyThreadPoolAddOperation(stackName,"default");
  op.get("operation").set("write-attribute");
  op.get("name").set("keepalive-time");
  op.get("value").set(999);
  result=services.executeOperation(op);
  Assert.assertEquals(SUCCESS,result.get(OUTCOME).asString());
  op=Operations.createReadResourceOperation(getSubsystemAddress());
  op.get(ModelDescriptionConstants.INCLUDE_ALIASES).set("true");
  op.get(ModelDescriptionConstants.RECURSIVE).set("true");
  result=services.executeOperation(op);
  Assert.assertEquals(SUCCESS,result.get(OUTCOME).asString());
  op=Util.createOperation(ModelDescriptionConstants.READ_RESOURCE_DESCRIPTION_OPERATION,getSubsystemAddress());
  op.get(ModelDescriptionConstants.INCLUDE_ALIASES).set("true");
  op.get(ModelDescriptionConstants.RECURSIVE).set("true");
  result=services.executeOperation(op);
  Assert.assertEquals(SUCCESS,result.get(OUTCOME).asString());
  result=services.executeOperation(getLegacyTransportRemoveOperation(stackName));
  Assert.assertEquals(SUCCESS,result.get(OUTCOME).asString());
  result=services.executeOperation(getLegacyTransportAddOperation(stackName,"TCP"));
  Assert.assertEquals(SUCCESS,result.get(OUTCOME).asString());
  result=services.executeOperation(getLegacyTransportRemoveOperation(stackName));
  Assert.assertEquals(SUCCESS,result.get(OUTCOME).asString());
  result=services.executeOperation(getLegacyTransportRemoveOperation(stackName));
  Assert.assertEquals(FAILED,result.get(OUTCOME).asString());
}
