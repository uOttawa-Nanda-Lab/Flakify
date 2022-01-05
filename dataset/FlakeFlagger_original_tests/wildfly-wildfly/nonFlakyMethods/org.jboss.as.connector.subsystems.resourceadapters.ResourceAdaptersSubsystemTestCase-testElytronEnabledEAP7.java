@Test public void testElytronEnabledEAP7() throws Exception {
  testRejectingTransformer7ElytronEnabled("resource-adapters-pool-elytron-enabled.xml",ModelTestControllerVersion.EAP_7_0_0,ModelVersion.create(4,0,0));
}
