@Test public void testFailedTransformersEAP700() throws Exception {
  final KernelServicesBuilder builder=createKernelServicesBuilder(AdditionalInitialization.MANAGEMENT);
  final ModelVersion legacyVersion=ModelVersion.create(1,1,0);
  final ModelTestControllerVersion controllerVersion=ModelTestControllerVersion.EAP_7_0_0;
  builder.createLegacyKernelServicesBuilder(AdditionalInitialization.MANAGEMENT,controllerVersion,legacyVersion).addMavenResourceURL(controllerVersion.getMavenGroupId() + ":wildfly-batch-jberet:" + controllerVersion.getMavenGavVersion()).addMavenResourceURL(controllerVersion.getCoreMavenGroupId() + ":wildfly-threads:" + controllerVersion.getCoreVersion());
  final KernelServices mainServices=builder.build();
  final KernelServices legacyServices=mainServices.getLegacyServices(legacyVersion);
  Assert.assertNotNull(legacyServices);
  Assert.assertTrue("main services did not boot",mainServices.isSuccessfulBoot());
  Assert.assertTrue(legacyServices.isSuccessfulBoot());
  final List<ModelNode> ops=builder.parseXmlResource("/default-subsystem.xml");
  ModelTestUtils.checkFailedTransformedBootOperations(mainServices,legacyVersion,ops,new FailedOperationTransformationConfig().addFailedAttribute(PathAddress.pathAddress(BatchSubsystemDefinition.SUBSYSTEM_PATH),new FailedOperationTransformationConfig.NewAttributesConfig(BatchSubsystemDefinition.SECURITY_DOMAIN)));
}
