/** 
 * Test list ordering
 * @throws Exception
 */
public void testOrdering() throws Exception {
  List<Filter> filters=new ArrayList<Filter>();
  filters.add(new PrefixFilter(Bytes.toBytes("yyy")));
  filters.add(new PageFilter(MAX_PAGES));
  Filter filterMPONE=new FilterList(FilterList.Operator.MUST_PASS_ONE,filters);
  filterMPONE.reset();
  assertFalse(filterMPONE.filterAllRemaining());
  byte[] rowkey=Bytes.toBytes("yyyyyyyy");
  for (int i=0; i < MAX_PAGES; i++) {
    assertFalse(filterMPONE.filterRowKey(rowkey,0,rowkey.length));
    KeyValue kv=new KeyValue(rowkey,rowkey,Bytes.toBytes(i),Bytes.toBytes(i));
    assertTrue(Filter.ReturnCode.INCLUDE == filterMPONE.filterKeyValue(kv));
    assertFalse(filterMPONE.filterRow());
  }
  rowkey=Bytes.toBytes("xxxxxxx");
  for (int i=0; i < MAX_PAGES; i++) {
    assertFalse(filterMPONE.filterRowKey(rowkey,0,rowkey.length));
    KeyValue kv=new KeyValue(rowkey,rowkey,Bytes.toBytes(i),Bytes.toBytes(i));
    assertTrue(Filter.ReturnCode.INCLUDE == filterMPONE.filterKeyValue(kv));
    assertFalse(filterMPONE.filterRow());
  }
  rowkey=Bytes.toBytes("yyy");
  for (int i=0; i < MAX_PAGES; i++) {
    assertFalse(filterMPONE.filterRowKey(rowkey,0,rowkey.length));
    KeyValue kv=new KeyValue(rowkey,rowkey,Bytes.toBytes(i),Bytes.toBytes(i));
    assertTrue(Filter.ReturnCode.INCLUDE == filterMPONE.filterKeyValue(kv));
    assertFalse(filterMPONE.filterRow());
  }
}
