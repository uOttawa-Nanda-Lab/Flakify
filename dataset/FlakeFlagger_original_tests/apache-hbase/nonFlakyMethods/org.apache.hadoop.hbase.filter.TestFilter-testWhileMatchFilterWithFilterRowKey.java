/** 
 * Tests the the  {@link WhileMatchFilter} works in combination with a{@link Filter} that uses the{@link Filter#filterRowKey(byte[],int,int)} method.See HBASE-2258.
 * @throws Exception
 */
public void testWhileMatchFilterWithFilterRowKey() throws Exception {
  Scan s=new Scan();
  String prefix="testRowOne";
  WhileMatchFilter filter=new WhileMatchFilter(new PrefixFilter(Bytes.toBytes(prefix)));
  s.setFilter(filter);
  InternalScanner scanner=this.region.getScanner(s);
  while (true) {
    ArrayList<KeyValue> values=new ArrayList<KeyValue>();
    boolean isMoreResults=scanner.next(values);
    if (!isMoreResults || !Bytes.toString(values.get(0).getRow()).startsWith(prefix)) {
      Assert.assertTrue("The WhileMatchFilter should now filter all remaining",filter.filterAllRemaining());
    }
    if (!isMoreResults) {
      break;
    }
  }
}
