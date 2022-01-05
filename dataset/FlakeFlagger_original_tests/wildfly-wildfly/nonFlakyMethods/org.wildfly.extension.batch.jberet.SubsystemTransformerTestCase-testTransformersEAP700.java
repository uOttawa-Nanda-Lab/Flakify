@Test public void testTransformersEAP700() throws Exception {
  final KernelServicesBuilder builder=createKernelServicesBuilder(createAdditionalInitialization()).setSubsystemXmlResource("/default-subsystem_1_0.xml");
  final ModelVersion legacyVersion=ModelVersion.create(1,1,0);
  final ModelTestControllerVersion controllerVersion=ModelTestControllerVersion.EAP_7_0_0;
  builder.createLegacyKernelServicesBuilder(createAdditionalInitialization(),controllerVersion,legacyVersion).addMavenResourceURL(controllerVersion.getMavenGroupId() + ":wildfly-batch-jberet:" + controllerVersion.getMavenGavVersion()).addMavenResourceURL(controllerVersion.getCoreMavenGroupId() + ":wildfly-threads:" + controllerVersion.getCoreVersion());
  final KernelServices mainServices=builder.build();
  assertTrue(mainServices.isSuccessfulBoot());
  final KernelServices legacyServices=mainServices.getLegacyServices(legacyVersion);
  assertNotNull(legacyServices);
  assertTrue(legacyServices.isSuccessfulBoot());
  checkSubsystemModelTransformation(mainServices,legacyVersion,null,false);
}
