public void testGet_Basic_NotFound() throws IOException {
  byte[] row=Bytes.toBytes("testrow");
  byte[] fam=Bytes.toBytes("testfamily");
  byte[] qf1=Bytes.toBytes("testqualifier1");
  byte[] qf2=Bytes.toBytes("testqualifier2");
  byte[] qf3=Bytes.toBytes("testqualifier3");
  byte[] val=Bytes.toBytes("testval");
  KeyValue add1=new KeyValue(row,fam,qf1,val);
  KeyValue add3=new KeyValue(row,fam,qf3,val);
  memstore.add(add1);
  memstore.add(add3);
  Get get=new Get(row);
  NavigableSet<byte[]> columns=new TreeSet<byte[]>(Bytes.BYTES_COMPARATOR);
  columns.add(qf2);
  long ttl=Long.MAX_VALUE;
  QueryMatcher matcher=new QueryMatcher(get,fam,columns,ttl,KeyValue.KEY_COMPARATOR,1);
  List<KeyValue> result=new ArrayList<KeyValue>();
  boolean res=memstore.get(matcher,result);
  assertEquals(false,res);
}
