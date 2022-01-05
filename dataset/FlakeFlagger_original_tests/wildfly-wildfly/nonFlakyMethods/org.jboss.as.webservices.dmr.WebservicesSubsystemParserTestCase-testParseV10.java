@Test public void testParseV10() throws Exception {
  KernelServices services=createKernelServicesBuilder(AdditionalInitialization.MANAGEMENT).setSubsystemXmlResource("ws-subsystem.xml").build();
  ModelNode model=services.readWholeModel().get("subsystem",getMainSubsystemName());
  standardSubsystemTest("ws-subsystem.xml",false);
  checkSubsystemBasics(model);
}
