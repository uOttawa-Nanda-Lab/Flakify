@Test public void testTxStats() throws Exception {
  final String subsystemXml=getSubsystemXml();
  final KernelServices kernelServices=super.createKernelServicesBuilder(createAdditionalInitialization()).setSubsystemXml(subsystemXml).build();
  Assert.assertTrue("Subsystem boot failed!",kernelServices.isSuccessfulBoot());
  ModelNode operation=createReadAttributeOperation(CommonAttributes.NUMBER_OF_SYSTEM_ROLLBACKS);
  ModelNode result=kernelServices.executeOperation(operation);
  Assert.assertEquals("success",result.get("outcome").asString());
  Assert.assertEquals(TxStats.getInstance().getNumberOfSystemRollbacks(),result.get(ModelDescriptionConstants.RESULT).asLong());
  operation=createReadAttributeOperation(CommonAttributes.AVERAGE_COMMIT_TIME);
  result=kernelServices.executeOperation(operation);
  Assert.assertEquals("success",result.get("outcome").asString());
  Assert.assertEquals(TxStats.getInstance().getAverageCommitTime(),result.get(ModelDescriptionConstants.RESULT).asLong());
}
