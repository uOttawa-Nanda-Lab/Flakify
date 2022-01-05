public void testGet_memstoreAndSnapShot() throws IOException {
  byte[] row=Bytes.toBytes("testrow");
  byte[] fam=Bytes.toBytes("testfamily");
  byte[] qf1=Bytes.toBytes("testqualifier1");
  byte[] qf2=Bytes.toBytes("testqualifier2");
  byte[] qf3=Bytes.toBytes("testqualifier3");
  byte[] qf4=Bytes.toBytes("testqualifier4");
  byte[] qf5=Bytes.toBytes("testqualifier5");
  byte[] val=Bytes.toBytes("testval");
  Get get=new Get(row);
  NavigableSet<byte[]> columns=new TreeSet<byte[]>(Bytes.BYTES_COMPARATOR);
  columns.add(qf2);
  columns.add(qf4);
  long ttl=Long.MAX_VALUE;
  QueryMatcher matcher=new QueryMatcher(get,fam,columns,ttl,KeyValue.KEY_COMPARATOR,1);
  memstore.add(new KeyValue(row,fam,qf1,val));
  memstore.add(new KeyValue(row,fam,qf2,val));
  memstore.add(new KeyValue(row,fam,qf3,val));
  memstore.snapshot();
  assertEquals(3,memstore.snapshot.size());
  assertEquals(0,memstore.kvset.size());
  memstore.add(new KeyValue(row,fam,qf4,val));
  memstore.add(new KeyValue(row,fam,qf5,val));
  assertEquals(2,memstore.kvset.size());
  List<KeyValue> result=new ArrayList<KeyValue>();
  boolean res=memstore.get(matcher,result);
  assertEquals(true,res);
}
