/** 
 * Tests access to subsystem attributes
 */
@Test public void testSubsystemReadWriteOperations() throws Exception {
  KernelServices services=this.buildKernelServices();
  ModelNode result=services.executeOperation(getSubsystemReadOperation(JGroupsSubsystemResourceDefinition.Attribute.DEFAULT_CHANNEL));
  Assert.assertEquals(result.get(FAILURE_DESCRIPTION).asString(),SUCCESS,result.get(OUTCOME).asString());
  Assert.assertEquals("ee",result.get(RESULT).asString());
  result=services.executeOperation(getSubsystemWriteOperation(JGroupsSubsystemResourceDefinition.Attribute.DEFAULT_CHANNEL,"bridge"));
  Assert.assertEquals(result.get(FAILURE_DESCRIPTION).asString(),SUCCESS,result.get(OUTCOME).asString());
  result=services.executeOperation(getSubsystemReadOperation(JGroupsSubsystemResourceDefinition.Attribute.DEFAULT_CHANNEL));
  Assert.assertEquals(result.get(FAILURE_DESCRIPTION).asString(),SUCCESS,result.get(OUTCOME).asString());
  Assert.assertEquals("bridge",result.get(RESULT).asString());
}
