/** 
 * Verify that  {@link QueryMatcher} only skips expired KeyValueinstances and does not exit early from the row (skipping later non-expired KeyValues).  This version mimics a Get with wildcard-inferred column qualifiers.
 * @throws IOException
 */
public void testMatch_ExpiredWildcard() throws IOException {
  long testTTL=1000;
  MatchCode[] expected=new MatchCode[]{MatchCode.INCLUDE,MatchCode.INCLUDE,MatchCode.SKIP,MatchCode.INCLUDE,MatchCode.SKIP,MatchCode.NEXT};
  QueryMatcher qm=new QueryMatcher(get,fam2,null,testTTL,rowComparator,1);
  long now=System.currentTimeMillis();
  KeyValue[] kvs=new KeyValue[]{new KeyValue(row1,fam2,col1,now - 100,data),new KeyValue(row1,fam2,col2,now - 50,data),new KeyValue(row1,fam2,col3,now - 5000,data),new KeyValue(row1,fam2,col4,now - 500,data),new KeyValue(row1,fam2,col5,now - 10000,data),new KeyValue(row2,fam1,col1,now - 10,data)};
  List<MatchCode> actual=new ArrayList<MatchCode>(kvs.length);
  for (  KeyValue kv : kvs) {
    actual.add(qm.match(kv));
  }
  assertEquals(expected.length,actual.size());
  for (int i=0; i < expected.length; i++) {
    if (PRINT) {
      System.out.println("expected " + expected[i] + ", actual "+ actual.get(i));
    }
    assertEquals(expected[i],actual.get(i));
  }
}
