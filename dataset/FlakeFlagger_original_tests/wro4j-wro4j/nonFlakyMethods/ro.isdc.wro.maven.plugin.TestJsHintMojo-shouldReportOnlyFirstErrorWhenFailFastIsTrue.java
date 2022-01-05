@Test(expected=MojoExecutionException.class) public void shouldReportOnlyFirstErrorWhenFailFastIsTrue() throws Exception {
  final JsHintMojo jsHintMojo=(JsHintMojo)getMojo();
  jsHintMojo.setFailFast(true);
  try {
    executeResourcesWithErrors();
  }
  finally {
    final LintReport<?> lintReport=jsHintMojo.getLintReport();
    assertEquals(1,lintReport.getReports().size());
  }
}
