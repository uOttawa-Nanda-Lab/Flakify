@Test public void testParseAndMarshalModel() throws Exception {
  String subsystemXml=readResource("securitysubsystemv1.xml");
  KernelServices servicesA=createKernelServicesBuilder(AdditionalInitialization.MANAGEMENT).setSubsystemXml(subsystemXml).build();
  ModelNode modelA=servicesA.readWholeModel();
  String marshalled=servicesA.getPersistedSubsystemXml();
  servicesA.shutdown();
  KernelServices servicesB=createKernelServicesBuilder(AdditionalInitialization.MANAGEMENT).setSubsystemXml(marshalled).build();
  ModelNode modelB=servicesB.readWholeModel();
  super.compare(modelA,modelB);
}
