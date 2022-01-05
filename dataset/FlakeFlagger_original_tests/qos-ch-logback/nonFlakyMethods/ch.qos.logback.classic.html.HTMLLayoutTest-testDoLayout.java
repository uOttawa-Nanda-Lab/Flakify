@Test public void testDoLayout() throws Exception {
  ILoggingEvent le=createLoggingEvent();
  String result=layout.getFileHeader();
  result+=layout.getPresentationHeader();
  result+=layout.doLayout(le);
  result+=layout.getPresentationFooter();
  result+=layout.getFileFooter();
  Document doc=parseOutput(result);
  Element rootElement=doc.getRootElement();
  rootElement.toString();
  assertEquals(2,rootElement.elements().size());
  Element bodyElement=(Element)rootElement.elements().get(1);
  Element tableElement=(Element)bodyElement.elements().get(3);
  assertEquals("table",tableElement.getName());
  Element trElement=(Element)tableElement.elements().get(1);
{
    Element tdElement=(Element)trElement.elements().get(0);
    assertEquals("DEBUG",tdElement.getText());
  }
{
    Element tdElement=(Element)trElement.elements().get(1);
    String regex=ClassicTestConstants.NAKED_MAIN_REGEX;
    System.out.println(tdElement.getText());
    assertTrue(tdElement.getText().matches(regex));
  }
{
    Element tdElement=(Element)trElement.elements().get(2);
    assertEquals("test message",tdElement.getText());
  }
}
