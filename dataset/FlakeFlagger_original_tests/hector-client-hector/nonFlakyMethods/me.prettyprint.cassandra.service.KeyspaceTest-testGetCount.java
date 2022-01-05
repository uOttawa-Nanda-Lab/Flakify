@Test public void testGetCount() throws HectorException {
  for (int i=0; i < 100; i++) {
    ColumnPath cp=new ColumnPath("Standard1");
    cp.setColumn(bytes("testInsertAndGetAndRemove_" + i));
    keyspace.insert("testGetCount",cp,StringSerializer.get().toByteBuffer("testInsertAndGetAndRemove_value_" + i));
  }
  ColumnParent clp=new ColumnParent("Standard1");
  ColumnPath cp=new ColumnPath("Standard1");
  keyspace.remove("testGetCount",cp);
}
