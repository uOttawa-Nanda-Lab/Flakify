/** 
 * WFLY-2640 and WFLY-8141 
 */
@Test public void testCCMHandling() throws Exception {
  String xml=readResource("jca-minimal.xml");
  final KernelServices services=createKernelServicesBuilder(createAdditionalInitialization()).setSubsystemXml(xml).build();
  assertTrue("Subsystem boot failed!",services.isSuccessfulBoot());
  final ModelNode model=services.readWholeModel();
  ModelNode ccm=model.get("subsystem","jca","cached-connection-manager","cached-connection-manager");
  assertTrue(ccm.isDefined());
  assertTrue(ccm.hasDefined("install"));
  PathAddress ccmAddress=PathAddress.pathAddress("subsystem","jca").append("cached-connection-manager","cached-connection-manager");
  ModelNode writeOp=Util.getWriteAttributeOperation(ccmAddress,"install",true);
  services.executeForResult(writeOp);
  ModelNode readOp=Util.getReadAttributeOperation(ccmAddress,"install");
  ModelNode result=services.executeForResult(readOp);
  assertTrue(result.asBoolean());
  ModelNode removeOp=Util.createRemoveOperation(ccmAddress);
  services.executeForResult(removeOp);
  result=services.executeForResult(readOp);
  assertFalse(result.asBoolean());
  services.executeForResult(writeOp);
  ModelNode addOp=Util.createAddOperation(ccmAddress);
  addOp.get("debug").set(true);
  services.executeForFailure(addOp);
  ModelNode undefineOp=Util.createEmptyOperation("undefine-attribute",ccmAddress);
  undefineOp.get("name").set("install");
  services.executeForResult(undefineOp);
  result=services.executeForResult(readOp);
  assertFalse(result.asBoolean());
  services.executeForResult(addOp);
  ModelNode readOp2=Util.getReadAttributeOperation(ccmAddress,"debug");
  result=services.executeForResult(readOp2);
  assertTrue(result.asBoolean());
  services.executeForFailure(addOp);
  services.executeForResult(removeOp);
  result=services.executeForResult(readOp2);
  assertFalse(result.asBoolean());
  services.executeForResult(addOp);
  result=services.executeForResult(readOp2);
  assertTrue(result.asBoolean());
}
