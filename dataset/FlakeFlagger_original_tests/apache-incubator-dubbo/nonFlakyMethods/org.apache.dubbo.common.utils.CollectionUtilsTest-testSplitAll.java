@Test public void testSplitAll() throws Exception {
  assertNull(CollectionUtils.splitAll(null,null));
  assertNull(CollectionUtils.splitAll(null,"-"));
  assertTrue(CollectionUtils.splitAll(new HashMap<String,List<String>>(),"-").isEmpty());
  Map<String,List<String>> input=new HashMap<String,List<String>>();
  input.put("key1",Arrays.asList("1:a","2:b","3:c"));
  input.put("key2",Arrays.asList("1:a","2:b"));
  input.put("key3",null);
  input.put("key4",new ArrayList<String>());
  Map<String,Map<String,String>> expected=new HashMap<String,Map<String,String>>();
  expected.put("key1",CollectionUtils.toStringMap("1","a","2","b","3","c"));
  expected.put("key2",CollectionUtils.toStringMap("1","a","2","b"));
  expected.put("key3",null);
  expected.put("key4",new HashMap<String,String>());
  assertEquals(expected,CollectionUtils.splitAll(input,":"));
}
