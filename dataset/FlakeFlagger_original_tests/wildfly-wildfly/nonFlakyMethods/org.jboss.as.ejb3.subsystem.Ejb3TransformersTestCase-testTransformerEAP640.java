@Test public void testTransformerEAP640() throws Exception {
  ModelTestControllerVersion controller=ModelTestControllerVersion.EAP_6_4_0;
  testTransformation(ModelVersion.create(1,3,0),controller,formatLegacySubsystemArtifact(controller),formatArtifact("org.jboss.as:jboss-as-threads:%s",controller));
}
