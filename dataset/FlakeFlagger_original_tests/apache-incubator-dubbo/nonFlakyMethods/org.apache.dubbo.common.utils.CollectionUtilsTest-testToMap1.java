@Test public void testToMap1() throws Exception {
  assertTrue(CollectionUtils.toMap().isEmpty());
  Map<String,Integer> expected=new HashMap<String,Integer>();
  expected.put("a",1);
  expected.put("b",2);
  expected.put("c",3);
  assertEquals(expected,CollectionUtils.toMap("a",1,"b",2,"c",3));
}
