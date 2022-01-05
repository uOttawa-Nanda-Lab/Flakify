public void testMatch_Wildcard() throws IOException {
  List<MatchCode> expected=new ArrayList<MatchCode>();
  expected.add(MatchCode.INCLUDE);
  expected.add(MatchCode.INCLUDE);
  expected.add(MatchCode.INCLUDE);
  expected.add(MatchCode.INCLUDE);
  expected.add(MatchCode.INCLUDE);
  expected.add(MatchCode.NEXT);
  QueryMatcher qm=new QueryMatcher(get,fam2,null,ttl,rowComparator,1);
  List<KeyValue> memstore=new ArrayList<KeyValue>();
  memstore.add(new KeyValue(row1,fam2,col1,data));
  memstore.add(new KeyValue(row1,fam2,col2,data));
  memstore.add(new KeyValue(row1,fam2,col3,data));
  memstore.add(new KeyValue(row1,fam2,col4,data));
  memstore.add(new KeyValue(row1,fam2,col5,data));
  memstore.add(new KeyValue(row2,fam1,col1,data));
  List<MatchCode> actual=new ArrayList<MatchCode>();
  for (  KeyValue kv : memstore) {
    actual.add(qm.match(kv));
  }
  assertEquals(expected.size(),actual.size());
  for (int i=0; i < expected.size(); i++) {
    assertEquals(expected.get(i),actual.get(i));
    if (PRINT) {
      System.out.println("expected " + expected.get(i) + ", actual "+ actual.get(i));
    }
  }
}
