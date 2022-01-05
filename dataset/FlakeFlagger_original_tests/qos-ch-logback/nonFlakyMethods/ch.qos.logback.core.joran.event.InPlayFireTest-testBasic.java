@Test public void testBasic() throws JoranException {
  ListenAction listenAction=new ListenAction();
  rulesMap.put(new ElementSelector("fire"),listenAction);
  TrivialConfigurator gc=new TrivialConfigurator(rulesMap);
  gc.setContext(context);
  gc.doConfigure(CoreTestConstants.TEST_SRC_PREFIX + "input/joran/fire1.xml");
  assertEquals(5,listenAction.getSeList().size());
  assertTrue(listenAction.getSeList().get(0) instanceof StartEvent);
  assertTrue(listenAction.getSeList().get(1) instanceof StartEvent);
  assertTrue(listenAction.getSeList().get(2) instanceof BodyEvent);
  assertTrue(listenAction.getSeList().get(3) instanceof EndEvent);
}
