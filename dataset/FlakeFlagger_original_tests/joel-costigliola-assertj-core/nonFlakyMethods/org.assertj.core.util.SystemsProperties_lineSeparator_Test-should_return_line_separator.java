@Test public void should_return_line_separator(){
  assertEquals(System.getProperty("line.separator"),SystemProperties.LINE_SEPARATOR);
}
