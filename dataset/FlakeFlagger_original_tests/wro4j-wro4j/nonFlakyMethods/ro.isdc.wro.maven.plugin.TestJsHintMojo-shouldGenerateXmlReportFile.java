@Test public void shouldGenerateXmlReportFile() throws Exception {
  final File reportFile=WroUtil.createTempFile();
  try {
    ((JsHintMojo)getMojo()).setReportFile(reportFile);
    getMojo().setOptions("undef, browser");
    getMojo().setTargetGroups(null);
    getMojo().setFailNever(true);
    getMojo().setIgnoreMissingResources(true);
    getMojo().execute();
  }
  finally {
    assertTrue(reportFile.length() > 1000);
    FileUtils.deleteQuietly(reportFile);
  }
}
