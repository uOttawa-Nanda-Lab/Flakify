@Test public void testJoinList() throws Exception {
  List<String> list=Arrays.asList();
  assertEquals("",CollectionUtils.join(list,"/"));
  list=Arrays.asList("x");
  assertEquals("x",CollectionUtils.join(list,"-"));
  list=Arrays.asList("a","b");
  assertEquals("a/b",CollectionUtils.join(list,"/"));
}
