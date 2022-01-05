@SuppressWarnings("deprecation") @Test public void testDistributedCacheJDBCStoreReadWriteOperation() throws Exception {
  ModelNode stringKeyedTable=createStringKeyedTable();
  String subsystemXml=getSubsystemXml();
  KernelServices servicesA=this.createKernelServicesBuilder().setSubsystemXml(subsystemXml).build();
  ModelNode result=servicesA.executeOperation(getJDBCCacheStoreReadOperation("maximal",DistributedCacheResourceDefinition.WILDCARD_PATH.getKey(),"dist",JDBCStoreResourceDefinition.Attribute.DATA_SOURCE));
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  Assert.assertEquals("ExampleDS",result.get(RESULT).asString());
  result=servicesA.executeOperation(getJDBCCacheStoreWriteOperation("maximal",DistributedCacheResourceDefinition.WILDCARD_PATH.getKey(),"dist",JDBCStoreResourceDefinition.Attribute.DATA_SOURCE,"new-datasource"));
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  result=servicesA.executeOperation(getJDBCCacheStoreReadOperation("maximal",DistributedCacheResourceDefinition.WILDCARD_PATH.getKey(),"dist",JDBCStoreResourceDefinition.Attribute.DATA_SOURCE));
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  Assert.assertEquals("new-datasource",result.get(RESULT).asString());
  result=servicesA.executeOperation(getJDBCCacheStoreReadOperation("maximal",DistributedCacheResourceDefinition.WILDCARD_PATH.getKey(),"dist",MixedKeyedJDBCStoreResourceDefinition.DeprecatedAttribute.STRING_TABLE));
  Assert.assertEquals(result.toString(),SUCCESS,result.get(OUTCOME).asString());
  Assert.assertEquals(stringKeyedTable.asPropertyList().size(),result.get(RESULT).asPropertyList().size());
  for (  Property property : stringKeyedTable.asPropertyList()) {
    Assert.assertTrue(result.get(RESULT).hasDefined(property.getName()));
    Assert.assertEquals(property.getValue(),result.get(RESULT).get(property.getName()));
  }
}
