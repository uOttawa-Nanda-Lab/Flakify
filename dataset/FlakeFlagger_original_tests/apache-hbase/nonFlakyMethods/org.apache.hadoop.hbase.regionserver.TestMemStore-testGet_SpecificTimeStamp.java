public void testGet_SpecificTimeStamp() throws IOException {
  byte[] row=Bytes.toBytes("testrow");
  byte[] fam=Bytes.toBytes("testfamily");
  byte[] qf1=Bytes.toBytes("testqualifier1");
  byte[] qf2=Bytes.toBytes("testqualifier2");
  byte[] qf3=Bytes.toBytes("testqualifier3");
  byte[] val=Bytes.toBytes("testval");
  long ts1=System.currentTimeMillis();
  long ts2=ts1++;
  long ts3=ts2++;
  Get get=new Get(row);
  get.setTimeStamp(ts2);
  NavigableSet<byte[]> columns=new TreeSet<byte[]>(Bytes.BYTES_COMPARATOR);
  columns.add(qf1);
  columns.add(qf2);
  columns.add(qf3);
  long ttl=Long.MAX_VALUE;
  QueryMatcher matcher=new QueryMatcher(get,fam,columns,ttl,KeyValue.KEY_COMPARATOR,1);
  List<KeyValue> expected=new ArrayList<KeyValue>();
  KeyValue kv1=new KeyValue(row,fam,qf1,ts2,val);
  KeyValue kv2=new KeyValue(row,fam,qf2,ts2,val);
  KeyValue kv3=new KeyValue(row,fam,qf3,ts2,val);
  expected.add(kv1);
  expected.add(kv2);
  expected.add(kv3);
  memstore.add(new KeyValue(row,fam,qf1,ts1,val));
  memstore.add(new KeyValue(row,fam,qf2,ts1,val));
  memstore.add(new KeyValue(row,fam,qf3,ts1,val));
  memstore.add(kv1);
  memstore.add(kv2);
  memstore.add(kv3);
  memstore.add(new KeyValue(row,fam,qf1,ts3,val));
  memstore.add(new KeyValue(row,fam,qf2,ts3,val));
  memstore.add(new KeyValue(row,fam,qf3,ts3,val));
  List<KeyValue> result=new ArrayList<KeyValue>();
  memstore.get(matcher,result);
  assertEquals(expected.size(),result.size());
  for (int i=0; i < expected.size(); i++) {
    assertEquals(expected.get(i),result.get(i));
  }
}
