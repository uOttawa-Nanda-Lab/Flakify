@SuppressWarnings("unchecked") @Test public void testPresentationHeader() throws Exception {
  String header=layout.getFileHeader();
  String presentationHeader=layout.getPresentationHeader();
  header=header + presentationHeader;
  Document doc=parseOutput(header + "</table></body></html>");
  Element rootElement=doc.getRootElement();
  Element bodyElement=rootElement.element("body");
  Element tableElement=bodyElement.element("table");
  Element trElement=tableElement.element("tr");
  List<Element> elementList=trElement.elements();
  assertEquals("Level",elementList.get(0).getText());
  assertEquals("Thread",elementList.get(1).getText());
  assertEquals("Message",elementList.get(2).getText());
}
