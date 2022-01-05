@Test public void testTransformersEAP70() throws Exception {
  ModelVersion modelVersion=ModelVersion.create(3,0,0);
  KernelServicesBuilder builder=createKernelServicesBuilder(AdditionalInitialization.MANAGEMENT).setSubsystemXmlResource("subsystem_4_0-transformers.xml");
  builder.createLegacyKernelServicesBuilder(AdditionalInitialization.MANAGEMENT,ModelTestControllerVersion.EAP_7_0_0,modelVersion).addMavenResourceURL("org.jboss.eap:wildfly-weld:" + ModelTestControllerVersion.EAP_7_0_0.getMavenGavVersion()).dontPersistXml();
  KernelServices mainServices=builder.build();
  KernelServices legacyServices=mainServices.getLegacyServices(modelVersion);
  assertTrue(mainServices.isSuccessfulBoot());
  assertTrue(legacyServices.isSuccessfulBoot());
  checkSubsystemModelTransformation(mainServices,modelVersion);
}
