@Test public void testLocalCacheReadWriteOperation() throws Exception {
  String subsystemXml=getSubsystemXml();
  KernelServices servicesA=this.createKernelServicesBuilder().setSubsystemXml(subsystemXml).build();
  ModelNode readOperation=getCacheReadOperation("maximal",LocalCacheResourceDefinition.WILDCARD_PATH.getKey(),"local",CacheResourceDefinition.Attribute.STATISTICS_ENABLED);
  ModelNode result=servicesA.executeOperation(readOperation);
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  Assert.assertTrue(result.get(RESULT).asBoolean());
  ModelNode writeOperation=getCacheWriteOperation("maximal",LocalCacheResourceDefinition.WILDCARD_PATH.getKey(),"local",CacheResourceDefinition.Attribute.STATISTICS_ENABLED,"false");
  result=servicesA.executeOperation(writeOperation);
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  result=servicesA.executeOperation(readOperation);
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  Assert.assertFalse(result.get(RESULT).asBoolean());
}
