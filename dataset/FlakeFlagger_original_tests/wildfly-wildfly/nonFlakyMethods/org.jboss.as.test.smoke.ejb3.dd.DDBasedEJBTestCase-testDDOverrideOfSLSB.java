/** 
 * Tests that the overrides in the ejb-jar.xml for a SLSB are honoured, and the bean is invokable through its exposed views
 * @throws Exception
 */
@Test public void testDDOverrideOfSLSB() throws Exception {
  Context ctx=new InitialContext();
  String ejbName=DDOverrideSLSB.class.getSimpleName();
  String jndiName="java:global/" + MODULE_NAME + "/"+ ejbName;
  Echo bean=(Echo)ctx.lookup(jndiName);
  String msg="Another simple echo!";
  String echo=bean.echo(msg);
  Assert.assertEquals("Unexpected return message from bean",msg,echo);
}
