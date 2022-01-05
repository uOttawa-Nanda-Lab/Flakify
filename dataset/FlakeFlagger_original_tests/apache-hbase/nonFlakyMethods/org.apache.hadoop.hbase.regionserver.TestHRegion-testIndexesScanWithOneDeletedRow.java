public void testIndexesScanWithOneDeletedRow() throws IOException {
  byte[] tableName=Bytes.toBytes("testIndexesScanWithOneDeletedRow");
  byte[] family=Bytes.toBytes("family");
  String method="testIndexesScanWithOneDeletedRow";
  initHRegion(tableName,method,new HBaseConfiguration(),family);
  Put put=new Put(Bytes.toBytes(1L));
  put.add(family,qual1,1L,Bytes.toBytes(1L));
  region.put(put);
  region.flushcache();
  Delete delete=new Delete(Bytes.toBytes(1L),1L,null);
  region.delete(delete,null,true);
  put=new Put(Bytes.toBytes(2L));
  put.add(family,qual1,2L,Bytes.toBytes(2L));
  region.put(put);
  Scan idxScan=new Scan();
  idxScan.addFamily(family);
  idxScan.setFilter(new FilterList(FilterList.Operator.MUST_PASS_ALL,Arrays.<Filter>asList(new SingleColumnValueFilter(family,qual1,CompareFilter.CompareOp.GREATER_OR_EQUAL,new BinaryComparator(Bytes.toBytes(0L))),new SingleColumnValueFilter(family,qual1,CompareFilter.CompareOp.LESS_OR_EQUAL,new BinaryComparator(Bytes.toBytes(3L))))));
  InternalScanner scanner=region.getScanner(idxScan);
  List<KeyValue> res=new ArrayList<KeyValue>();
  while (scanner.next(res))   ;
  assertEquals(1L,res.size());
}
