@Test public void shouldAcceptValidReportFormat() throws Exception {
  runPluginWithReportFormat(ReportXmlFormatter.FormatterType.CHECKSTYLE.getFormat());
}
