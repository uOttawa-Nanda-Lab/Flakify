public void testGetTableDescriptor() throws IOException {
  HTableDescriptor local=new HTable(conf,TABLE).getTableDescriptor();
  assertEquals(remoteTable.getTableDescriptor(),local);
}
