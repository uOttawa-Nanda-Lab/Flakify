@Test public void noName() throws JoranException {
  simpleConfigurator.doConfigure(DEFINE_INPUT_DIR + NONAME_XML);
  String inContextFoo=context.getProperty("foo");
  assertNull(inContextFoo);
  checker.assertContainsMatch(Status.ERROR,"Missing property name for property definer. Near \\[define\\] line 1");
}
