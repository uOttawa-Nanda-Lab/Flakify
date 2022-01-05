@Test public void testIgnoreMarker() throws NullPointerException, EvaluationException, JoranException {
  JoranConfigurator jc=new JoranConfigurator();
  LoggerContext loggerContext=new LoggerContext();
  jc.setContext(loggerContext);
  jc.doConfigure(ClassicTestConstants.JORAN_INPUT_PREFIX + "ignore.xml");
  Map evalMap=(Map)loggerContext.getObject(CoreConstants.EVALUATOR_MAP);
  assertNotNull(evalMap);
  Logger logger=loggerContext.getLogger("xx");
  JaninoEventEvaluator evaluator=(JaninoEventEvaluator)evalMap.get("IGNORE_EVAL");
  LoggingEvent event=new LoggingEvent("foo",logger,Level.DEBUG,"Hello world",null,null);
  Marker ignoreMarker=MarkerFactory.getMarker("IGNORE");
  event.setMarker(ignoreMarker);
  assertTrue(evaluator.evaluate(event));
  logger.debug("hello",new Exception("test"));
  logger.debug(ignoreMarker,"hello ignore",new Exception("test"));
}
