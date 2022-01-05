@Test public void testRejectTransformersEAP700() throws Exception {
  testRejectTransformers7(ModelTestControllerVersion.EAP_7_0_0,MODEL_VERSION_EAP70,new FailedOperationTransformationConfig().addFailedAttribute(PathAddress.pathAddress(TransactionExtension.SUBSYSTEM_PATH),new FailedOperationTransformationConfig.NewAttributesConfig("maximum-timeout")));
}
