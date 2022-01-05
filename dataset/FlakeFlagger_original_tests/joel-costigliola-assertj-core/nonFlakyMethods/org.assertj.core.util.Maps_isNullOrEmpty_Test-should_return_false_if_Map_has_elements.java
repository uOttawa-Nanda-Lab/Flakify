@Test public void should_return_false_if_Map_has_elements(){
  Map<String,Integer> map=new HashMap<>();
  map.put("First",1);
  assertFalse(Maps.isNullOrEmpty(map));
}
