@Test public void testBadClass() throws JoranException {
  simpleConfigurator.doConfigure(DEFINE_INPUT_DIR + BADCLASS_XML);
  String inContextFoo=context.getProperty("foo");
  assertNull(inContextFoo);
  checker.assertContainsMatch(Status.ERROR,"Could not create an PropertyDefiner of type");
}
