@Test public void testRejectTransformersEAP640() throws Exception {
  testRejectTransformers(ModelTestControllerVersion.EAP_6_4_0,MODEL_VERSION_EAP64,new FailedOperationTransformationConfig().addFailedAttribute(PathAddress.pathAddress(TransactionExtension.SUBSYSTEM_PATH),new FailedOperationTransformationConfig.NewAttributesConfig("maximum-timeout")));
}
