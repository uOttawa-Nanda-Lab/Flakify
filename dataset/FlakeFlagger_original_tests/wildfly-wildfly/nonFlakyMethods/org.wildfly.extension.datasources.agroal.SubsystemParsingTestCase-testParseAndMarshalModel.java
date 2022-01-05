/** 
 * Starts a controller with a given subsystem xml and then checks that a second controller started with the xml marshaled from the first one results in the same model
 */
@Test public void testParseAndMarshalModel() throws Exception {
  String subsystemXml="<subsystem xmlns=\"" + AgroalNamespace.CURRENT.getUriString() + "\"/>";
  KernelServices servicesA=super.createKernelServicesBuilder(null).setSubsystemXml(subsystemXml).build();
  ModelNode modelA=servicesA.readWholeModel();
  String marshaled=servicesA.getPersistedSubsystemXml();
  KernelServices servicesB=super.createKernelServicesBuilder(null).setSubsystemXml(marshaled).build();
  ModelNode modelB=servicesB.readWholeModel();
  super.compare(modelA,modelB);
}
