/** 
 * Tests that the ejb-jar.xml and annotations are merged correctly for a SFSB, and the bean is invokable through its exposed views
 * @throws Exception
 */
@Test public void testPartialDDSFSB() throws Exception {
  Context ctx=new InitialContext();
  String ejbName=PartialDDSFSB.class.getSimpleName();
  String localBusinessInterfaceViewJndiName="java:global/" + MODULE_NAME + "/"+ ejbName+ "!"+ Echo.class.getName();
  Echo localBusinessIntfView=(Echo)ctx.lookup(localBusinessInterfaceViewJndiName);
  String msgOne="This is message one!";
  Assert.assertEquals("Unexpected return message from bean",msgOne,localBusinessIntfView.echo(msgOne));
  String noInterfaceViewJndiName="java:global/" + MODULE_NAME + "/"+ ejbName+ "!"+ PartialDDSFSB.class.getName();
  PartialDDSFSB noInterfaceView=(PartialDDSFSB)ctx.lookup(noInterfaceViewJndiName);
  String msgTwo="Yet another message!";
  Assert.assertEquals("Unexpected return message from no-interface view of bean",msgTwo,noInterfaceView.echo(msgTwo));
}
