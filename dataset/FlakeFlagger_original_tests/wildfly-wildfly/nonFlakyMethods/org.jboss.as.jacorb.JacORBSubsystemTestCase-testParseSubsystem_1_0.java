@Test public void testParseSubsystem_1_0() throws Exception {
  List<ModelNode> operations=super.parse(ModelTestUtils.readResource(this.getClass(),"subsystem-1.0.xml"));
  Assert.assertEquals(1,operations.size());
  ModelNode addSubsystem=operations.get(0);
  Assert.assertEquals(ADD,addSubsystem.get(OP).asString());
  PathAddress addr=PathAddress.pathAddress(addSubsystem.get(OP_ADDR));
  Assert.assertEquals(1,addr.size());
  PathElement element=addr.getElement(0);
  Assert.assertEquals(SUBSYSTEM,element.getKey());
  Assert.assertEquals(JacORBExtension.SUBSYSTEM_NAME,element.getValue());
}
