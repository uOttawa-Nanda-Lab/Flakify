@Test public void testCacheContainerRemoveRemoveSequence() throws Exception {
  String subsystemXml=getSubsystemXml();
  KernelServices servicesA=this.createKernelServicesBuilder().setSubsystemXml(subsystemXml).build();
  ModelNode addContainerOp=getCacheContainerAddOperation("maximal2");
  ModelNode removeContainerOp=getCacheContainerRemoveOperation("maximal2");
  ModelNode addCacheOp=getCacheAddOperation("maximal2",LocalCacheResourceDefinition.WILDCARD_PATH.getKey(),"fred");
  ModelNode result=servicesA.executeOperation(addContainerOp);
  Assert.assertEquals(result.get(FAILURE_DESCRIPTION).asString(),SUCCESS,result.get(OUTCOME).asString());
  result=servicesA.executeOperation(addCacheOp);
  Assert.assertEquals(result.get(FAILURE_DESCRIPTION).asString(),SUCCESS,result.get(OUTCOME).asString());
  result=servicesA.executeOperation(removeContainerOp);
  Assert.assertEquals(result.get(FAILURE_DESCRIPTION).asString(),SUCCESS,result.get(OUTCOME).asString());
  result=servicesA.executeOperation(removeContainerOp);
  Assert.assertEquals(result.toString(),FAILED,result.get(OUTCOME).asString());
}
