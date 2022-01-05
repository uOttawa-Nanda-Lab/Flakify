@Test public void testCacheContainerReadWriteOperation() throws Exception {
  String subsystemXml=getSubsystemXml();
  KernelServices servicesA=this.createKernelServicesBuilder().setSubsystemXml(subsystemXml).build();
  ModelNode result=servicesA.executeOperation(getCacheContainerReadOperation("maximal",CacheContainerResourceDefinition.Attribute.DEFAULT_CACHE));
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  Assert.assertEquals("local",result.get(RESULT).asString());
  result=servicesA.executeOperation(getCacheContainerWriteOperation("maximal",CacheContainerResourceDefinition.Attribute.DEFAULT_CACHE,"new-default-cache"));
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  result=servicesA.executeOperation(getCacheContainerReadOperation("maximal",CacheContainerResourceDefinition.Attribute.DEFAULT_CACHE));
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  Assert.assertEquals("new-default-cache",result.get(RESULT).asString());
}
