@Test public void testRejectTransformersEAP710() throws Exception {
  testRejectTransformers7(ModelTestControllerVersion.EAP_7_1_0,MODEL_VERSION_EAP71,new FailedOperationTransformationConfig().addFailedAttribute(PathAddress.pathAddress(TransactionExtension.SUBSYSTEM_PATH),new FailedOperationTransformationConfig.NewAttributesConfig("maximum-timeout")));
}
