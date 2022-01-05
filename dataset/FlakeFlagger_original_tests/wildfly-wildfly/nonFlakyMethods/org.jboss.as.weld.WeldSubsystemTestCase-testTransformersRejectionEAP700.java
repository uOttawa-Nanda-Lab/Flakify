@Test public void testTransformersRejectionEAP700() throws Exception {
  ModelVersion modelVersion=ModelVersion.create(3,0,0);
  KernelServicesBuilder builder=createKernelServicesBuilder(AdditionalInitialization.MANAGEMENT);
  builder.createLegacyKernelServicesBuilder(AdditionalInitialization.MANAGEMENT,ModelTestControllerVersion.EAP_7_0_0,modelVersion).addMavenResourceURL("org.jboss.eap:wildfly-weld:" + ModelTestControllerVersion.EAP_7_0_0.getMavenGavVersion()).dontPersistXml();
  KernelServices mainServices=builder.build();
  assertTrue(mainServices.isSuccessfulBoot());
  assertTrue(mainServices.getLegacyServices(modelVersion).isSuccessfulBoot());
  ModelTestUtils.checkFailedTransformedBootOperations(mainServices,modelVersion,parse(getSubsystemXml("subsystem-reject.xml")),new FailedOperationTransformationConfig().addFailedAttribute(PathAddress.pathAddress(WeldExtension.PATH_SUBSYSTEM),ChainedConfig.createBuilder(WeldResourceDefinition.NON_PORTABLE_MODE_ATTRIBUTE,WeldResourceDefinition.REQUIRE_BEAN_DESCRIPTOR_ATTRIBUTE).addConfig(new NewAttributesConfig(WeldResourceDefinition.THREAD_POOL_SIZE_ATTRIBUTE)).build()));
}
