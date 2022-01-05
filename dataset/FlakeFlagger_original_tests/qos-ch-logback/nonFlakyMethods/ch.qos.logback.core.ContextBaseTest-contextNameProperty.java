@Test public void contextNameProperty(){
  assertNull(context.getProperty(CoreConstants.CONTEXT_NAME_KEY));
  String HELLO="hello";
  context.setName(HELLO);
  assertEquals(HELLO,context.getProperty(CoreConstants.CONTEXT_NAME_KEY));
  assertEquals(HELLO,context.getProperty("CONTEXT_NAME"));
}
