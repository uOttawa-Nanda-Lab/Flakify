@Test public void testBasic() throws Exception {
  jee.setExpression("message.equals(\"Some message\")");
  jee.start();
  StatusPrinter.print(loggerContext);
  ILoggingEvent event=makeLoggingEvent(null);
  assertTrue(jee.evaluate(event));
}
