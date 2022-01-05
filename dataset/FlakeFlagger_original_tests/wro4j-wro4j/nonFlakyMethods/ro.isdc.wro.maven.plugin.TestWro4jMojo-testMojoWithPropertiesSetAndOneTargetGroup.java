@Test public void testMojoWithPropertiesSetAndOneTargetGroup() throws Exception {
  victim.setTargetGroups("g1");
  victim.setIgnoreMissingResources(true);
  victim.execute();
}
