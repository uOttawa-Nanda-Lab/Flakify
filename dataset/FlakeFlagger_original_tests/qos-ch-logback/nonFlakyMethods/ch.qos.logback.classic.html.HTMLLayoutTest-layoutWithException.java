@SuppressWarnings("unchecked") @Test public void layoutWithException() throws Exception {
  layout.setPattern("%level %thread %msg %ex");
  LoggingEvent le=createLoggingEvent();
  le.setThrowableProxy(new ThrowableProxy(new Exception("test Exception")));
  String result=layout.doLayout(le);
  String stringToParse=layout.getFileHeader();
  stringToParse=stringToParse + layout.getPresentationHeader();
  stringToParse+=result;
  stringToParse+="</table></body></html>";
  Document doc=parseOutput(stringToParse);
  Element rootElement=doc.getRootElement();
  Element bodyElement=rootElement.element("body");
  Element tableElement=bodyElement.element("table");
  List<Element> trElementList=tableElement.elements();
  Element exceptionRowElement=trElementList.get(2);
  Element exceptionElement=exceptionRowElement.element("td");
  assertEquals(3,tableElement.elements().size());
  assertTrue(exceptionElement.getText().contains("java.lang.Exception: test Exception"));
}
