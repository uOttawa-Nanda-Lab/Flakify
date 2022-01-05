@Test public void testMultipleConditionsInExpression() throws NullPointerException, EvaluationException {
  LoggerContext loggerContext=new LoggerContext();
  Logger logger=loggerContext.getLogger("xx");
  JaninoEventEvaluator ee=new JaninoEventEvaluator();
  ee.setName("testEval");
  ee.setContext(loggerContext);
  ee.setExpression("message.contains(\"stacktrace\") && message.contains(\"logging\")");
  ee.start();
  String message="stacktrace bla bla logging";
  ILoggingEvent event=new LoggingEvent(this.getClass().getName(),logger,Level.DEBUG,message,null,null);
  assertTrue(ee.evaluate(event));
}
