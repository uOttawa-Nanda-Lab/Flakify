/** 
 * Tests that all possible local view bindings of a Stateless bean are available.
 * @throws Exception
 */
@Test public void testLocalBindingsOnSLSB() throws Exception {
  Context ctx=new InitialContext();
  String ejbName=DDBasedSLSB.class.getSimpleName();
  Echo bean=(Echo)ctx.lookup("java:global/" + MODULE_NAME + "/"+ ejbName+ "!"+ Echo.class.getName());
  String msg="Simple echo!";
  String echo=bean.echo(msg);
  Assert.assertEquals("Unexpected return message from bean",msg,echo);
}
