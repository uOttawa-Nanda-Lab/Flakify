@Test(expected=MojoExecutionException.class) public void testOnlyCssDestinationFolderSet() throws Exception {
  victim.setCssDestinationFolder(cssDestinationFolder);
  victim.setDestinationFolder(null);
  victim.execute();
}
