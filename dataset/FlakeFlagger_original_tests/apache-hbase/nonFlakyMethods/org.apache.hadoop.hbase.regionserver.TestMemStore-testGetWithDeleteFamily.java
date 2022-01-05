public void testGetWithDeleteFamily() throws IOException {
  byte[] row=Bytes.toBytes("testrow");
  byte[] fam=Bytes.toBytes("testfamily");
  byte[] qf1=Bytes.toBytes("testqualifier1");
  byte[] qf2=Bytes.toBytes("testqualifier2");
  byte[] qf3=Bytes.toBytes("testqualifier3");
  byte[] val=Bytes.toBytes("testval");
  long ts=System.nanoTime();
  KeyValue put1=new KeyValue(row,fam,qf1,ts,val);
  KeyValue put2=new KeyValue(row,fam,qf2,ts,val);
  KeyValue put3=new KeyValue(row,fam,qf3,ts,val);
  KeyValue put4=new KeyValue(row,fam,qf3,ts + 1,val);
  memstore.add(put1);
  memstore.add(put2);
  memstore.add(put3);
  memstore.add(put4);
  KeyValue del=new KeyValue(row,fam,null,ts,KeyValue.Type.DeleteFamily,val);
  memstore.delete(del);
  List<KeyValue> expected=new ArrayList<KeyValue>();
  expected.add(del);
  expected.add(put4);
  assertEquals(2,memstore.kvset.size());
  int i=0;
  for (  KeyValue kv : memstore.kvset) {
    assertEquals(expected.get(i++),kv);
  }
}
