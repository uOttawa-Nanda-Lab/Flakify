@Test public void testRejectionsEAP6() throws Exception {
  testTransformer("subsystem.xml",ModelTestControllerVersion.EAP_6_4_0,ModelVersion.create(1,3),"jboss-as-naming");
}
