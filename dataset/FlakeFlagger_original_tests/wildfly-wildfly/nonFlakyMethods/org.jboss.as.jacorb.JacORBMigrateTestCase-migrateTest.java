@Test public void migrateTest() throws Exception {
  String subsystemXml=readResource("subsystem.xml");
  NewSubsystemAdditionalInitialization additionalInitialization=new NewSubsystemAdditionalInitialization();
  KernelServices services=createKernelServicesBuilder(additionalInitialization).setSubsystemXml(subsystemXml).build();
  ModelNode model=services.readWholeModel();
  Assert.assertFalse(additionalInitialization.extensionAdded);
  Assert.assertTrue(model.get(SUBSYSTEM).hasDefined(JacORBExtension.SUBSYSTEM_NAME));
  Assert.assertFalse(model.get(SUBSYSTEM).hasDefined(IIOPExtension.SUBSYSTEM_NAME));
  ModelNode migrateOp=new ModelNode();
  migrateOp.get(OP).set("migrate");
  migrateOp.get(OP_ADDR).set(PathAddress.pathAddress(PathElement.pathElement(SUBSYSTEM,JacORBExtension.SUBSYSTEM_NAME)).toModelNode());
  checkOutcome(services.executeOperation(migrateOp));
  model=services.readWholeModel();
  Assert.assertTrue(additionalInitialization.extensionAdded);
  Assert.assertFalse(model.get(SUBSYSTEM).hasDefined(JacORBExtension.SUBSYSTEM_NAME));
  Assert.assertTrue(model.get(SUBSYSTEM).hasDefined(IIOPExtension.SUBSYSTEM_NAME));
  ModelNode newSubsystem=model.get(SUBSYSTEM).get("iiop-openjdk");
  Assert.assertTrue(newSubsystem.get("export-corbaloc").equals(new ModelNode(true)));
  Assert.assertTrue(newSubsystem.get("confidentiality").equals(new ModelNode("required")));
  Assert.assertTrue(newSubsystem.get("iona").equals(new ModelNode(true)));
}
