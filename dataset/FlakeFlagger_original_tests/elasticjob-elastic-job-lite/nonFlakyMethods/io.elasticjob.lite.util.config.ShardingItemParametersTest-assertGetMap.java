@Test public void assertGetMap(){
  Map<Integer,String> expected=new HashMap<>(3);
  expected.put(0,"A");
  expected.put(1,"B");
  expected.put(2,"C");
  assertThat(new ShardingItemParameters("0=A,1=B,2=C").getMap(),is(expected));
}
