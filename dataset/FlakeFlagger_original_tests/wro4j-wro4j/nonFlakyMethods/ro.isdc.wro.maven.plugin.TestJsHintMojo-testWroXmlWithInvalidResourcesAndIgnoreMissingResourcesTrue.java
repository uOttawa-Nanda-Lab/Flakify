@Test public void testWroXmlWithInvalidResourcesAndIgnoreMissingResourcesTrue() throws Exception {
  setWroWithInvalidResources();
  getMojo().setIgnoreMissingResources(true);
  getMojo().execute();
}
