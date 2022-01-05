/** 
 * Test that the model created from the xml looks as expected
 */
@Test public void testInstallIntoController() throws Exception {
  String subsystemXml="<subsystem xmlns=\"" + AgroalNamespace.CURRENT.getUriString() + "\"/>";
  KernelServices services=super.createKernelServicesBuilder(null).setSubsystemXml(subsystemXml).build();
  ModelNode model=services.readWholeModel();
  Assert.assertTrue(model.get(SUBSYSTEM).hasDefined(AgroalExtension.SUBSYSTEM_NAME));
}
