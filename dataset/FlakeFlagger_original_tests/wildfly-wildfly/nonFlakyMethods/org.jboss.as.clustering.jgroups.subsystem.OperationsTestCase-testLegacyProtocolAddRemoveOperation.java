@Test public void testLegacyProtocolAddRemoveOperation() throws Exception {
  KernelServices services=this.buildKernelServices();
  testProtocolAddRemoveOperation(services,"MERGE2");
  testProtocolAddRemoveOperation(services,"pbcast.NAKACK");
  testProtocolAddRemoveOperation(services,"UNICAST2");
}
