@Test public void shouldGenerateXmlReportFileWithDefaultFormat() throws Exception {
  generateAndCompareReportUsingFormat(null,"csslint-default.xml");
}
