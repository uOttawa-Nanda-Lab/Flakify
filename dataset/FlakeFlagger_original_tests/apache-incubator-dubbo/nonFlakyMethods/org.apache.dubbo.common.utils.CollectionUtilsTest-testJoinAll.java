@Test public void testJoinAll() throws Exception {
  assertNull(CollectionUtils.joinAll(null,null));
  assertNull(CollectionUtils.joinAll(null,"-"));
  Map<String,List<String>> expected=new HashMap<String,List<String>>();
  expected.put("key1",Arrays.asList("1:a","2:b","3:c"));
  expected.put("key2",Arrays.asList("1:a","2:b"));
  expected.put("key3",null);
  expected.put("key4",new ArrayList<String>());
  Map<String,Map<String,String>> input=new HashMap<String,Map<String,String>>();
  input.put("key1",CollectionUtils.toStringMap("1","a","2","b","3","c"));
  input.put("key2",CollectionUtils.toStringMap("1","a","2","b"));
  input.put("key3",null);
  input.put("key4",new HashMap<String,String>());
  Map<String,List<String>> output=CollectionUtils.joinAll(input,":");
  for (  Map.Entry<String,List<String>> entry : output.entrySet()) {
    if (entry.getValue() == null)     continue;
    Collections.sort(entry.getValue());
  }
  assertEquals(expected,output);
}
