/** 
 * Test "must pass one"
 * @throws Exception
 */
public void testMPONE() throws Exception {
  List<Filter> filters=new ArrayList<Filter>();
  filters.add(new PageFilter(MAX_PAGES));
  filters.add(new WhileMatchFilter(new PrefixFilter(Bytes.toBytes("yyy"))));
  Filter filterMPONE=new FilterList(FilterList.Operator.MUST_PASS_ONE,filters);
  filterMPONE.reset();
  assertFalse(filterMPONE.filterAllRemaining());
  byte[] rowkey=Bytes.toBytes("yyyyyyyyy");
  for (int i=0; i < MAX_PAGES - 1; i++) {
    assertFalse(filterMPONE.filterRowKey(rowkey,0,rowkey.length));
    assertFalse(filterMPONE.filterRow());
    KeyValue kv=new KeyValue(rowkey,rowkey,Bytes.toBytes(i),Bytes.toBytes(i));
    assertTrue(Filter.ReturnCode.INCLUDE == filterMPONE.filterKeyValue(kv));
  }
  rowkey=Bytes.toBytes("z");
  assertFalse(filterMPONE.filterRowKey(rowkey,0,rowkey.length));
  assertFalse(filterMPONE.filterRow());
  KeyValue kv=new KeyValue(rowkey,rowkey,Bytes.toBytes(0),Bytes.toBytes(0));
  assertTrue(Filter.ReturnCode.INCLUDE == filterMPONE.filterKeyValue(kv));
  rowkey=Bytes.toBytes("yyy");
  assertFalse(filterMPONE.filterRowKey(rowkey,0,rowkey.length));
  assertFalse(filterMPONE.filterRow());
  kv=new KeyValue(rowkey,rowkey,Bytes.toBytes(0),Bytes.toBytes(0));
  assertTrue(Filter.ReturnCode.INCLUDE == filterMPONE.filterKeyValue(kv));
  rowkey=Bytes.toBytes("z");
  assertTrue(filterMPONE.filterRowKey(rowkey,0,rowkey.length));
  assertTrue(filterMPONE.filterRow());
  assertTrue(filterMPONE.filterAllRemaining());
}
