@Test public void testSortSimpleName() throws Exception {
  List<String> list=new ArrayList<String>();
  list.add("aaa.z");
  list.add("b");
  list.add(null);
  list.add("zzz.a");
  list.add("c");
  list.add(null);
  List<String> sorted=CollectionUtils.sortSimpleName(list);
  assertNull(sorted.get(0));
  assertNull(sorted.get(1));
}
