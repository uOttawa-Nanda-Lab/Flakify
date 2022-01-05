@Test public void other(){
  List<String> result=p.combinations("aBCd");
  assertEquals(16,result.size());
  Set<String> witness=new HashSet<String>(result);
  assertEquals(16,witness.size());
}
