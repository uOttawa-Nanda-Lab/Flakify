@Test(expected=MojoExecutionException.class) public void testNoDestinationFolderSet() throws Exception {
  victim.setDestinationFolder(null);
  victim.execute();
}
