@Test public void testRejectionsEAP7() throws Exception {
  testTransformer("subsystem.xml",ModelTestControllerVersion.EAP_7_0_0,ModelVersion.create(2,0),"wildfly-naming");
}
