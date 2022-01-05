@Test public void testParseAndMarshalModel() throws Exception {
  String subsystemXml=readResource("identity-management-subsystem-1.0.xml");
  KernelServices servicesA=createKernelServicesBuilder(AdditionalInitialization.MANAGEMENT).setSubsystemXml(subsystemXml).build();
  ModelNode modelA=servicesA.readWholeModel();
  String marshalled=servicesA.getPersistedSubsystemXml();
  servicesA.shutdown();
  KernelServices servicesB=createKernelServicesBuilder(AdditionalInitialization.MANAGEMENT).setSubsystemXml(marshalled).build();
  ModelNode modelB=servicesB.readWholeModel();
  super.compare(modelA,modelB);
}
