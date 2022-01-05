@Test public void testInterceptorsOnSingleton() throws Exception {
  Context ctx=new InitialContext();
  String ejbName=InterceptedDDBean.class.getSimpleName();
  String jndiName="java:global/" + MODULE_NAME + "/"+ ejbName+ "!"+ InterceptedDDBean.class.getName();
  InterceptedDDBean interceptedBean=(InterceptedDDBean)ctx.lookup(jndiName);
  String msg="You will be intercepted!!!";
  String returnMsg=interceptedBean.echo(msg);
  String expectedReturnMsg=SimpleInterceptor.class.getName() + "#" + DDBasedInterceptor.class.getName()+ "#"+ msg;
  Assert.assertEquals("Unexpected return message from bean",expectedReturnMsg,returnMsg);
}
