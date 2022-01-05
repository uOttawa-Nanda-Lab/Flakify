@Test public void testContentOverridden() throws NamingException {
  Assert.assertEquals("OVERRIDDEN",initialContext.lookup("java:module/env/simpleString"));
}
