@Test public void testConnection() throws Exception {
  ModelNode testAddress=address.clone();
  testAddress.add("connection-definitions","Pool1");
  ModelNode op=new ModelNode();
  op.get(OP).set("test-connection-in-pool");
  op.get(OP_ADDR).set(testAddress);
  assertTrue(executeOperation(op).asBoolean());
}
