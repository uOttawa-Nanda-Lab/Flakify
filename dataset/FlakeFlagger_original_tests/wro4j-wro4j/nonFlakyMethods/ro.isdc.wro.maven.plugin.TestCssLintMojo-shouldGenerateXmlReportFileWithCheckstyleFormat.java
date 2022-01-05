@Test public void shouldGenerateXmlReportFileWithCheckstyleFormat() throws Exception {
  generateAndCompareReportUsingFormat(FormatterType.CHECKSTYLE.getFormat(),"csslint-checkstyle.xml");
}
