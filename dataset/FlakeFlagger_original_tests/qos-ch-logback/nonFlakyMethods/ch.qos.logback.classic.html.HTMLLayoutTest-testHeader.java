@Test public void testHeader() throws Exception {
  String header=layout.getFileHeader();
  Document doc=parseOutput(header + "</body></html>");
  Element rootElement=doc.getRootElement();
  assertNotNull(rootElement.element("body"));
}
