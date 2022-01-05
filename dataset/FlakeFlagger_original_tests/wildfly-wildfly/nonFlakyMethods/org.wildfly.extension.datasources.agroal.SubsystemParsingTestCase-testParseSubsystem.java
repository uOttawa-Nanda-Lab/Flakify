/** 
 * Tests that the xml is parsed into the correct operations
 */
@Test public void testParseSubsystem() throws Exception {
  String subsystemXml="<subsystem xmlns=\"" + AgroalNamespace.CURRENT.getUriString() + "\"/>";
  List<ModelNode> operations=super.parse(subsystemXml);
  Assert.assertEquals(1,operations.size());
  ModelNode addSubsystem=operations.get(0);
  Assert.assertEquals(ADD,addSubsystem.get(OP).asString());
  PathAddress address=PathAddress.pathAddress(addSubsystem.get(OP_ADDR));
  Assert.assertEquals(1,address.size());
  PathElement element=address.getElement(0);
  Assert.assertEquals(SUBSYSTEM,element.getKey());
  Assert.assertEquals(AgroalExtension.SUBSYSTEM_NAME,element.getValue());
}
