public void testGetWithDelete() throws IOException {
  byte[] row=Bytes.toBytes("testrow");
  byte[] fam=Bytes.toBytes("testfamily");
  byte[] qf1=Bytes.toBytes("testqualifier");
  byte[] val=Bytes.toBytes("testval");
  long ts1=System.nanoTime();
  KeyValue put1=new KeyValue(row,fam,qf1,ts1,val);
  long ts2=ts1 + 1;
  KeyValue put2=new KeyValue(row,fam,qf1,ts2,val);
  long ts3=ts2 + 1;
  KeyValue put3=new KeyValue(row,fam,qf1,ts3,val);
  memstore.add(put1);
  memstore.add(put2);
  memstore.add(put3);
  assertEquals(3,memstore.kvset.size());
  KeyValue del2=new KeyValue(row,fam,qf1,ts2,KeyValue.Type.Delete,val);
  memstore.delete(del2);
  List<KeyValue> expected=new ArrayList<KeyValue>();
  expected.add(put3);
  expected.add(del2);
  expected.add(put1);
  assertEquals(3,memstore.kvset.size());
  int i=0;
  for (  KeyValue kv : memstore.kvset) {
    assertEquals(expected.get(i++),kv);
  }
}
