/** 
 * Test "must pass all"
 * @throws Exception
 */
public void testMPALL() throws Exception {
  List<Filter> filters=new ArrayList<Filter>();
  filters.add(new PageFilter(MAX_PAGES));
  filters.add(new WhileMatchFilter(new PrefixFilter(Bytes.toBytes("yyy"))));
  Filter filterMPALL=new FilterList(FilterList.Operator.MUST_PASS_ALL,filters);
  filterMPALL.reset();
  assertFalse(filterMPALL.filterAllRemaining());
  byte[] rowkey=Bytes.toBytes("yyyyyyyyy");
  for (int i=0; i < MAX_PAGES - 1; i++) {
    assertFalse(filterMPALL.filterRowKey(rowkey,0,rowkey.length));
    KeyValue kv=new KeyValue(rowkey,rowkey,Bytes.toBytes(i),Bytes.toBytes(i));
    assertTrue(Filter.ReturnCode.INCLUDE == filterMPALL.filterKeyValue(kv));
  }
  filterMPALL.reset();
  rowkey=Bytes.toBytes("z");
  assertTrue(filterMPALL.filterRowKey(rowkey,0,rowkey.length));
  KeyValue kv=new KeyValue(rowkey,rowkey,rowkey,rowkey);
  assertTrue(Filter.ReturnCode.NEXT_ROW == filterMPALL.filterKeyValue(kv));
  assertTrue(filterMPALL.filterRow());
}
