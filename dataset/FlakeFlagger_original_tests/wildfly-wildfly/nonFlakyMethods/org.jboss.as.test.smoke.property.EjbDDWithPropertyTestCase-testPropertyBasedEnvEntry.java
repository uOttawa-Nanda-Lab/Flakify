@Test public void testPropertyBasedEnvEntry() throws Exception {
  Context ctx=new InitialContext();
  String ejbName=TestSessionBean.class.getSimpleName();
  TestBean bean=(TestBean)ctx.lookup("java:global/test/" + MODULE_NAME + "/"+ ejbName+ "!"+ TestBean.class.getName());
  assertEquals("foo" + System.getProperty("file.separator") + "bar",bean.getValue());
}
