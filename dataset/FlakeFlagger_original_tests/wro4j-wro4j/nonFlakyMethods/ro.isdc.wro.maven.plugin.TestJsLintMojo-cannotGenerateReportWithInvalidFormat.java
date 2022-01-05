@Test(expected=MojoExecutionException.class) public void cannotGenerateReportWithInvalidFormat() throws Exception {
  final File reportFile=WroUtil.createTempFile();
  final JsLintMojo mojo=(JsLintMojo)getMojo();
  try {
    mojo.setReportFile(reportFile);
    mojo.setReportFormat("INVALID");
    mojo.setOptions("undef, browser");
    mojo.setTargetGroups(null);
    mojo.setFailNever(true);
    mojo.setIgnoreMissingResources(true);
    mojo.execute();
  }
  finally {
    FileUtils.deleteQuietly(reportFile);
  }
}
