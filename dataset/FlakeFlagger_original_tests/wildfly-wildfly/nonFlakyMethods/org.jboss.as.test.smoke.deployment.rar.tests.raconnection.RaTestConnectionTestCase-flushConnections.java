@Test public void flushConnections() throws Exception {
  ModelNode testAddress=address.clone();
  testAddress.add("connection-definitions","Pool1");
  ModelNode op=new ModelNode();
  op.get(OP).set("flush-idle-connection-in-pool");
  op.get(OP_ADDR).set(testAddress);
  executeOperation(op);
}
