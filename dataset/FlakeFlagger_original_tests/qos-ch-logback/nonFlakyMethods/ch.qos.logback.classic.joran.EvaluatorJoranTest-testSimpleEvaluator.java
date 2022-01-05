@Test public void testSimpleEvaluator() throws NullPointerException, EvaluationException, JoranException {
  JoranConfigurator jc=new JoranConfigurator();
  LoggerContext loggerContext=new LoggerContext();
  jc.setContext(loggerContext);
  jc.doConfigure(ClassicTestConstants.JORAN_INPUT_PREFIX + "simpleEvaluator.xml");
  Map evalMap=(Map)loggerContext.getObject(CoreConstants.EVALUATOR_MAP);
  assertNotNull(evalMap);
  JaninoEventEvaluator evaluator=(JaninoEventEvaluator)evalMap.get("msgEval");
  assertNotNull(evaluator);
  Logger logger=loggerContext.getLogger("xx");
  ILoggingEvent event0=new LoggingEvent("foo",logger,Level.DEBUG,"Hello world",null,null);
  assertTrue(evaluator.evaluate(event0));
  ILoggingEvent event1=new LoggingEvent("foo",logger,Level.DEBUG,"random blurb",null,null);
  assertFalse(evaluator.evaluate(event1));
}
