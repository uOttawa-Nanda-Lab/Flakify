@Test public void noClass() throws JoranException {
  simpleConfigurator.doConfigure(DEFINE_INPUT_DIR + NOCLASS_XML);
  String inContextFoo=context.getProperty("foo");
  assertNull(inContextFoo);
  checker.assertContainsMatch(Status.ERROR,"Missing class name for property definer. Near \\[define\\] line 1");
}
