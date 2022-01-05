@Test(expected=MojoExecutionException.class) public void shouldReportAllErrorsWhenFailFastIsFalse() throws Exception {
  final JsHintMojo jsHintMojo=(JsHintMojo)getMojo();
  jsHintMojo.setFailFast(false);
  try {
    executeResourcesWithErrors();
  }
  finally {
    final LintReport<?> lintReport=jsHintMojo.getLintReport();
    assertEquals(2,lintReport.getReports().size());
  }
}
