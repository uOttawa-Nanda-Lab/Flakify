@Test public void testParseV12() throws Exception {
  KernelServices services=createKernelServicesBuilder(AdditionalInitialization.MANAGEMENT).setSubsystemXmlResource("ws-subsystem12.xml").build();
  ModelNode model=services.readWholeModel().get("subsystem",getMainSubsystemName());
  standardSubsystemTest("ws-subsystem12.xml",false);
  checkSubsystemBasics(model);
  checkEndpointConfigs(model);
  checkClientConfigs(model);
}
