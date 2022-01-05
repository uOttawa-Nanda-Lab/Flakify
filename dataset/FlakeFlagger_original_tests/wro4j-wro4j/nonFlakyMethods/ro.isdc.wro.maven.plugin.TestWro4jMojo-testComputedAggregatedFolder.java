@Test public void testComputedAggregatedFolder() throws Exception {
  setWroWithValidResources();
  victim.setWroManagerFactory(CssUrlRewriterWroManagerFactory.class.getName());
  victim.setIgnoreMissingResources(true);
  final File cssDestinationFolder=new File(this.destinationFolder,"subfolder");
  cssDestinationFolder.mkdir();
  victim.setCssDestinationFolder(cssDestinationFolder);
  victim.execute();
}
