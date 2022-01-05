/** 
 * Tests the the  {@link WhileMatchFilter} works in combination with a{@link Filter} that uses the{@link Filter#filterRow()} method.See HBASE-2258.
 * @throws Exception
 */
public void testWhileMatchFilterWithFilterRow() throws Exception {
  final int pageSize=4;
  Scan s=new Scan();
  WhileMatchFilter filter=new WhileMatchFilter(new PageFilter(pageSize));
  s.setFilter(filter);
  InternalScanner scanner=this.region.getScanner(s);
  int scannerCounter=0;
  while (true) {
    boolean isMoreResults=scanner.next(new ArrayList<KeyValue>());
    scannerCounter++;
    if (scannerCounter >= pageSize) {
      Assert.assertTrue("The WhileMatchFilter should now filter all remaining",filter.filterAllRemaining());
    }
    if (!isMoreResults) {
      break;
    }
  }
  Assert.assertEquals("The page filter returned more rows than expected",pageSize,scannerCounter);
}
