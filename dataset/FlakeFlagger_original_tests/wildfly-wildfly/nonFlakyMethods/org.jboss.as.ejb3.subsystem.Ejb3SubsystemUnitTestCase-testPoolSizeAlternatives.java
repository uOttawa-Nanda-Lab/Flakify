/** 
 * WFLY-7797 
 */
@Test public void testPoolSizeAlternatives() throws Exception {
  final String subsystemXml=getSubsystemXml();
  final KernelServices ks=createKernelServicesBuilder(AdditionalInitialization.MANAGEMENT).setSubsystemXml(subsystemXml).build();
  Assert.assertTrue("Subsystem boot failed!",ks.isSuccessfulBoot());
  PathAddress pa=PathAddress.pathAddress("subsystem","ejb3").append("strict-max-bean-instance-pool","slsb-strict-max-pool");
  ModelNode composite=Util.createEmptyOperation("composite",PathAddress.EMPTY_ADDRESS);
  ModelNode steps=composite.get("steps");
  ModelNode writeMax=Util.getWriteAttributeOperation(pa,"max-pool-size",5);
  ModelNode writeDerive=Util.getWriteAttributeOperation(pa,"derive-size","none");
  steps.add(writeMax);
  steps.add(writeDerive);
  ModelNode response=ks.executeOperation(composite);
  Assert.assertEquals(response.toString(),"success",response.get("outcome").asString());
  validatePoolConfig(ks,pa);
  steps.setEmptyList();
  writeMax.get("value").set(10);
  writeDerive.get("value").set("from-cpu-count");
  steps.add(writeMax);
  steps.add(writeDerive);
  ks.executeForFailure(composite);
  validatePoolConfig(ks,pa);
}
