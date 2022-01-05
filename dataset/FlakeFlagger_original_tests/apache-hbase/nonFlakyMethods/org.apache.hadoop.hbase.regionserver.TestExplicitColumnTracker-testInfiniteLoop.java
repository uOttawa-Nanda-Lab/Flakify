/** 
 * Regression test for HBASE-2545
 */
public void testInfiniteLoop(){
  TreeSet<byte[]> columns=new TreeSet<byte[]>(Bytes.BYTES_COMPARATOR);
  columns.addAll(Arrays.asList(new byte[][]{col2,col3,col5}));
  List<byte[]> scanner=Arrays.<byte[]>asList(new byte[][]{col1,col4});
  List<MatchCode> expected=Arrays.<MatchCode>asList(new MatchCode[]{MatchCode.SKIP,MatchCode.SKIP});
  runTest(1,columns,scanner,expected);
}
