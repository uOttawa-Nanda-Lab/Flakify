@Test public void shouldGenerateReportWithCheckstyleFormat() throws Exception {
  generateAndCompareReportFile(FormatterType.CHECKSTYLE.getFormat(),"jshint-checkstyle.xml");
}
