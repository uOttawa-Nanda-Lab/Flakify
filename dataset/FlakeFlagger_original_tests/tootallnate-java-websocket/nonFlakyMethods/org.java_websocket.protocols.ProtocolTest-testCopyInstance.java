@Test public void testCopyInstance() throws Exception {
  IProtocol protocol0=new Protocol("");
  IProtocol protoocl1=protocol0.copyInstance();
  assertEquals(protocol0,protoocl1);
  IProtocol protocol2=new Protocol("protocol");
  IProtocol protocol3=protocol2.copyInstance();
  assertEquals(protocol2,protocol3);
}
