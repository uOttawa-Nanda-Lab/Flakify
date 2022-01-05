@Test public void testWithParenthesis(){
  pl.setPattern("\\(%msg:%msg\\) %msg");
  pl.start();
  le=makeLoggingEvent(null);
  String val=pl.doLayout(le);
  assertEquals("(Some message:Some message) Some message",val);
}
