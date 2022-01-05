@Test public void testWroXmlWithInvalidResourcesAndIgnoreMissingResourcesTrue() throws Exception {
  setWroWithInvalidResources();
  victim.setIgnoreMissingResources(true);
  victim.execute();
}
