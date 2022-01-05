@Test(expected=MojoExecutionException.class) public void testOnlyJsDestinationFolderSet() throws Exception {
  victim.setJsDestinationFolder(jsDestinationFolder);
  victim.setDestinationFolder(null);
  victim.execute();
}
