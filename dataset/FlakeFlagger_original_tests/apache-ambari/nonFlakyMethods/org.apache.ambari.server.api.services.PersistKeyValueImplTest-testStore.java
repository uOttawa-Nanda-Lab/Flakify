@Test public void testStore() throws Exception {
  PersistKeyValueImpl impl=injector.getInstance(PersistKeyValueImpl.class);
  Map<String,String> map=impl.getAllKeyValues();
  assertEquals(0,map.size());
  impl.put("key1","value1");
  impl.put("key2","value2");
  map=impl.getAllKeyValues();
  assertEquals(2,map.size());
  assertEquals("value1",impl.getValue("key1"));
  assertEquals("value2",impl.getValue("key2"));
  assertEquals(map.get("key1"),impl.getValue("key1"));
  impl.put("key1","value1-2");
  assertEquals("value1-2",impl.getValue("key1"));
  assertEquals(2,map.size());
  StringBuilder largeValueBuilder=new StringBuilder();
  for (int i=0; i < 320; i++) {
    largeValueBuilder.append("0123456789");
  }
  String largeValue=largeValueBuilder.toString();
  impl.put("key3",largeValue);
  assertEquals(largeValue,impl.getValue("key3"));
}
