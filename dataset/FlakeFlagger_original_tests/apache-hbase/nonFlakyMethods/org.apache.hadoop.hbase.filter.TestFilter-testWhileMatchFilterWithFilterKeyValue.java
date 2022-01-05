/** 
 * Tests the the  {@link WhileMatchFilter} works in combination with a{@link Filter} that uses the{@link Filter#filterKeyValue(org.apache.hadoop.hbase.KeyValue)} method.See HBASE-2258.
 * @throws Exception
 */
public void testWhileMatchFilterWithFilterKeyValue() throws Exception {
  Scan s=new Scan();
  WhileMatchFilter filter=new WhileMatchFilter(new SingleColumnValueFilter(FAMILIES[0],QUALIFIERS_ONE[0],CompareOp.EQUAL,Bytes.toBytes("foo")));
  s.setFilter(filter);
  InternalScanner scanner=this.region.getScanner(s);
  while (true) {
    ArrayList<KeyValue> values=new ArrayList<KeyValue>();
    boolean isMoreResults=scanner.next(values);
    Assert.assertTrue("The WhileMatchFilter should now filter all remaining",filter.filterAllRemaining());
    if (!isMoreResults) {
      break;
    }
  }
}
