@Test public void testParseEmptySubsystem_1_0() throws Exception {
  String subsystemXml="<subsystem xmlns=\"" + JacORBSubsystemParser.Namespace.JacORB_1_0.getUriString() + "\">"+ "</subsystem>";
  List<ModelNode> operations=super.parse(subsystemXml);
  Assert.assertEquals(1,operations.size());
  ModelNode addSubsystem=operations.get(0);
  Assert.assertEquals(ADD,addSubsystem.get(OP).asString());
  PathAddress addr=PathAddress.pathAddress(addSubsystem.get(OP_ADDR));
  Assert.assertEquals(1,addr.size());
  PathElement element=addr.getElement(0);
  Assert.assertEquals(SUBSYSTEM,element.getKey());
  Assert.assertEquals(JacORBExtension.SUBSYSTEM_NAME,element.getValue());
}
