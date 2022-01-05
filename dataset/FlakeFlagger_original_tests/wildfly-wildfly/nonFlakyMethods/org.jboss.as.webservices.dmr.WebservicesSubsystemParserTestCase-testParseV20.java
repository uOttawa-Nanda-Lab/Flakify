@Test public void testParseV20() throws Exception {
  KernelServices services=createKernelServicesBuilder(AdditionalInitialization.MANAGEMENT).setSubsystemXmlResource("ws-subsystem20.xml").build();
  ModelNode model=services.readWholeModel().get("subsystem",getMainSubsystemName());
  checkSubsystemBasics(model);
  checkEndpointConfigs(model);
  checkClientConfigs(model);
}
