@Test public void shouldGenerateReportWithCheckstyleFormat() throws Exception {
  generateAndCompareReportFile(FormatterType.CHECKSTYLE.getFormat(),"jslint-checkstyle.xml");
}
