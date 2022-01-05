/** 
 * Tests that the xml is parsed into the correct operations
 */
@Test public void testParseSubsystem() throws Exception {
  List<ModelNode> operations=super.parse(getSubsystemXml());
  Assert.assertEquals(7,operations.size());
  ModelNode addSubsystem=operations.get(0);
  Assert.assertEquals(ADD,addSubsystem.get(OP).asString());
  PathAddress addr=PathAddress.pathAddress(addSubsystem.get(OP_ADDR));
  Assert.assertEquals(1,addr.size());
  PathElement element=addr.getElement(0);
  Assert.assertEquals(SUBSYSTEM,element.getKey());
  Assert.assertEquals(MailExtension.SUBSYSTEM_NAME,element.getValue());
}
