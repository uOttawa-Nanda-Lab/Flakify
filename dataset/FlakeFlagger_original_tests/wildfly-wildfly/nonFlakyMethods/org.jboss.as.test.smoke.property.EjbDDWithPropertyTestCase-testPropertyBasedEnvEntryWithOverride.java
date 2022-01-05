@Test public void testPropertyBasedEnvEntryWithOverride() throws Exception {
  Context ctx=new InitialContext();
  String ejbName=TestSessionBean.class.getSimpleName();
  TestBean bean=(TestBean)ctx.lookup("java:global/test/" + MODULE_NAME + "/"+ ejbName+ "!"+ TestBean.class.getName());
  assertEquals("foo-|-bar",bean.getValueOverride());
}
