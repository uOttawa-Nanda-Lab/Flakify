@Test public void testParseV11() throws Exception {
  KernelServices services=createKernelServicesBuilder(AdditionalInitialization.MANAGEMENT).setSubsystemXmlResource("ws-subsystem11.xml").build();
  ModelNode model=services.readWholeModel().get("subsystem",getMainSubsystemName());
  standardSubsystemTest("ws-subsystem11.xml",false);
  checkSubsystemBasics(model);
  checkEndpointConfigs(model);
}
