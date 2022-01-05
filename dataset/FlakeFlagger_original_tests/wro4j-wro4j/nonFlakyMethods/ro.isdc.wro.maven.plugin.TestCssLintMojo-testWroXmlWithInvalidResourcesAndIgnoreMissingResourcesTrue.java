@Test public void testWroXmlWithInvalidResourcesAndIgnoreMissingResourcesTrue() throws Exception {
  setWroWithInvalidResources();
  mojo.setIgnoreMissingResources(true);
  mojo.execute();
}
