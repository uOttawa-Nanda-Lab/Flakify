@Test public void should_return_toString_of_Map(){
  Map<String,String> map=new LinkedHashMap<>();
  map.put("key1","value1");
  map.put("key2","value2");
  assertEquals("{\"key1\"=\"value1\", \"key2\"=\"value2\"}",new StandardRepresentation().toStringOf(map));
}
