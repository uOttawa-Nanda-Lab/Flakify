@Test(expected=MojoExecutionException.class) public void shouldNotAcceptInvalidReportFormat() throws Exception {
  runPluginWithReportFormat("INVALID");
}
