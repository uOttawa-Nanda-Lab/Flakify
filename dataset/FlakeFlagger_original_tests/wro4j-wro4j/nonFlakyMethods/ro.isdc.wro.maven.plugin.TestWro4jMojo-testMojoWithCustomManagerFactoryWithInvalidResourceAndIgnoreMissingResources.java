@Test public void testMojoWithCustomManagerFactoryWithInvalidResourceAndIgnoreMissingResources() throws Exception {
  setWroWithInvalidResources();
  victim.setIgnoreMissingResources(true);
  victim.setWroManagerFactory(CustomManagerFactory.class.getName());
  victim.execute();
}
