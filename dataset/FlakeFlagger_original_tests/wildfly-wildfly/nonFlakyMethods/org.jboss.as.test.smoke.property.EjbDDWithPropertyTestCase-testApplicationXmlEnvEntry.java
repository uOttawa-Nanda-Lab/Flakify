@Test public void testApplicationXmlEnvEntry() throws Exception {
  Context ctx=new InitialContext();
  String value=(String)ctx.lookup("java:app/value");
  assertEquals("foo" + System.getProperty("file.separator") + "bar",value);
}
