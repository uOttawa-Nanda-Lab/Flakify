public void testGet_SingleVersion(){
  if (PRINT) {
    System.out.println("SingleVersion");
  }
  TreeSet<byte[]> columns=new TreeSet<byte[]>(Bytes.BYTES_COMPARATOR);
  columns.add(col2);
  columns.add(col4);
  List<MatchCode> expected=new ArrayList<MatchCode>();
  expected.add(MatchCode.SKIP);
  expected.add(MatchCode.INCLUDE);
  expected.add(MatchCode.SKIP);
  expected.add(MatchCode.INCLUDE);
  expected.add(MatchCode.DONE);
  int maxVersions=1;
  List<byte[]> scanner=new ArrayList<byte[]>();
  scanner.add(col1);
  scanner.add(col2);
  scanner.add(col3);
  scanner.add(col4);
  scanner.add(col5);
  runTest(maxVersions,columns,scanner,expected);
}
