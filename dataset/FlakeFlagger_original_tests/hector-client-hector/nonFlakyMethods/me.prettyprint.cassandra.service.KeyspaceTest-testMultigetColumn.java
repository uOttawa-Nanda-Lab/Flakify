@Test public void testMultigetColumn() throws HectorException {
  ColumnPath cp=new ColumnPath("Standard1");
  cp.setColumn(bytes("testMultigetColumn"));
  ArrayList<String> keys=new ArrayList<String>(100);
  for (int i=0; i < 100; i++) {
    keyspace.insert("testMultigetColumn_" + i,cp,StringSerializer.get().toByteBuffer("testMultigetColumn_value_" + i));
    keys.add("testMultigetColumn_" + i);
  }
  for (int i=0; i < 100; i++) {
    keyspace.remove("testMultigetColumn_" + i,cp);
  }
}
