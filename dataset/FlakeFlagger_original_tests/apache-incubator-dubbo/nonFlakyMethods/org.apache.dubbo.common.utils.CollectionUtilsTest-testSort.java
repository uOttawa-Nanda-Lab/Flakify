@Test public void testSort() throws Exception {
  List<Integer> list=new ArrayList<Integer>();
  list.add(100);
  list.add(10);
  list.add(20);
  List<Integer> expected=new ArrayList<Integer>();
  expected.add(10);
  expected.add(20);
  expected.add(100);
  assertEquals(expected,CollectionUtils.sort(list));
}
