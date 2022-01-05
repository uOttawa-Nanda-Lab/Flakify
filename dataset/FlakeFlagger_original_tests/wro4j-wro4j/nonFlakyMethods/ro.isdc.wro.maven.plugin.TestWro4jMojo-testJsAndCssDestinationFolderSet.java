@Test public void testJsAndCssDestinationFolderSet() throws Exception {
  victim.setIgnoreMissingResources(true);
  victim.setJsDestinationFolder(jsDestinationFolder);
  victim.setCssDestinationFolder(cssDestinationFolder);
  victim.execute();
}
