public void testMultipleVersionsSimple() throws Exception {
  MemStore m=new MemStore(KeyValue.COMPARATOR);
  byte[] row=Bytes.toBytes("testRow");
  byte[] family=Bytes.toBytes("testFamily");
  byte[] qf=Bytes.toBytes("testQualifier");
  long[] stamps={1,2,3};
  byte[][] values={Bytes.toBytes("value0"),Bytes.toBytes("value1"),Bytes.toBytes("value2")};
  KeyValue key0=new KeyValue(row,family,qf,stamps[0],values[0]);
  KeyValue key1=new KeyValue(row,family,qf,stamps[1],values[1]);
  KeyValue key2=new KeyValue(row,family,qf,stamps[2],values[2]);
  m.add(key0);
  m.add(key1);
  m.add(key2);
  assertTrue("Expected memstore to hold 3 values, actually has " + m.kvset.size(),m.kvset.size() == 3);
}
