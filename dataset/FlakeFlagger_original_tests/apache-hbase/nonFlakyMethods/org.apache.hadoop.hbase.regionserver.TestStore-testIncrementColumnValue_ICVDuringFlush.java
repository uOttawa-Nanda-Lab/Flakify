public void testIncrementColumnValue_ICVDuringFlush() throws IOException {
  init(this.getName());
  long oldValue=1L;
  long newValue=3L;
  this.store.add(new KeyValue(row,family,qf1,System.currentTimeMillis(),Bytes.toBytes(oldValue)));
  this.store.snapshot();
  this.store.add(new KeyValue(row,family,qf2,System.currentTimeMillis(),Bytes.toBytes(oldValue)));
  long ret=this.store.updateColumnValue(row,family,qf1,newValue);
  assertTrue(ret > 0);
  flushStore(store,id++);
  assertEquals(1,this.store.getStorefiles().size());
  assertEquals(2,this.store.memstore.kvset.size());
  Get get=new Get(row);
  get.addColumn(family,qf1);
  get.setMaxVersions();
  List<KeyValue> results=new ArrayList<KeyValue>();
  NavigableSet<byte[]> cols=new TreeSet<byte[]>();
  cols.add(qf1);
  this.store.get(get,cols,results);
  assertEquals(2,results.size());
  long ts1=results.get(0).getTimestamp();
  long ts2=results.get(1).getTimestamp();
  assertTrue(ts1 > ts2);
  assertEquals(newValue,Bytes.toLong(results.get(0).getValue()));
  assertEquals(oldValue,Bytes.toLong(results.get(1).getValue()));
}
