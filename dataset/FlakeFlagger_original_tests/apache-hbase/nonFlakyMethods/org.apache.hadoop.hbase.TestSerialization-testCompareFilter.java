public void testCompareFilter() throws Exception {
  Filter f=new RowFilter(CompareOp.EQUAL,new BinaryComparator(Bytes.toBytes("testRowOne-2")));
  byte[] bytes=Writables.getBytes(f);
  Filter ff=(Filter)Writables.getWritable(bytes,new RowFilter());
  assertNotNull(ff);
}
