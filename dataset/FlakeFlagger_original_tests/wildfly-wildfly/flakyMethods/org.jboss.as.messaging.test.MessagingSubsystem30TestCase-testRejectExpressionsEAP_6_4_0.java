@Test public void testRejectExpressionsEAP_6_4_0() throws Exception {
  KernelServicesBuilder builder=createKernelServicesBuilder(EAP_6_4_0,VERSION_1_4_0,PATH_FIXER,"empty_subsystem_3_0.xml");
  doTestRejectExpressions_1_4_0(builder);
}
