@Test public void testTransformerEAP7Elytron() throws Exception {
  testRejectingTransformerElytronEnabled(ModelTestControllerVersion.EAP_7_0_0,ModelVersion.create(4,0,0),"jca-full-elytron.xml");
}
