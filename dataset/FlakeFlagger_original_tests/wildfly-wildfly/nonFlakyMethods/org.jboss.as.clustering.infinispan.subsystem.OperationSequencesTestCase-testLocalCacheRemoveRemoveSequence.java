@Test public void testLocalCacheRemoveRemoveSequence() throws Exception {
  String subsystemXml=getSubsystemXml();
  KernelServices servicesA=this.createKernelServicesBuilder().setSubsystemXml(subsystemXml).build();
  ModelNode addOp=getCacheAddOperation("maximal",LocalCacheResourceDefinition.WILDCARD_PATH.getKey(),"fred");
  ModelNode removeOp=getCacheRemoveOperation("maximal",LocalCacheResourceDefinition.WILDCARD_PATH.getKey(),"fred");
  ModelNode result=servicesA.executeOperation(addOp);
  Assert.assertEquals(result.get(FAILURE_DESCRIPTION).asString(),SUCCESS,result.get(OUTCOME).asString());
  result=servicesA.executeOperation(removeOp);
  Assert.assertEquals(result.get(FAILURE_DESCRIPTION).asString(),SUCCESS,result.get(OUTCOME).asString());
  result=servicesA.executeOperation(removeOp);
  Assert.assertEquals(result.toString(),FAILED,result.get(OUTCOME).asString());
}
