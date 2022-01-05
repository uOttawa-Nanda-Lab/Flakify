public void testUpdate_SameColumns(){
  if (PRINT) {
    System.out.println("\nUpdate_SameColumns");
  }
  byte[] col1=Bytes.toBytes("col1");
  byte[] col2=Bytes.toBytes("col2");
  byte[] col3=Bytes.toBytes("col3");
  byte[] col4=Bytes.toBytes("col4");
  byte[] col5=Bytes.toBytes("col5");
  List<MatchCode> expected=new ArrayList<MatchCode>();
  int size=10;
  for (int i=0; i < size; i++) {
    expected.add(MatchCode.INCLUDE);
  }
  for (int i=0; i < 5; i++) {
    expected.add(MatchCode.SKIP);
  }
  int maxVersions=2;
  ColumnTracker wild=new WildcardColumnTracker(maxVersions);
  List<byte[]> scanner=new ArrayList<byte[]>();
  scanner.add(col1);
  scanner.add(col2);
  scanner.add(col3);
  scanner.add(col4);
  scanner.add(col5);
  List<MatchCode> result=new ArrayList<MatchCode>();
  for (int i=0; i < 3; i++) {
    for (    byte[] col : scanner) {
      result.add(wild.checkColumn(col,0,col.length));
    }
    wild.update();
  }
  assertEquals(expected.size(),result.size());
  for (int i=0; i < expected.size(); i++) {
    assertEquals(expected.get(i),result.get(i));
    if (PRINT) {
      System.out.println("Expected " + expected.get(i) + ", actual "+ result.get(i));
    }
  }
}
