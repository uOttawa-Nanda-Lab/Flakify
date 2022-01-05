@Test public void should_build_typed_map_from_source() throws Exception {
  Map<String,Object> source=new HashMap<>();
  source.put("string","value");
  source.put("int",10);
  TypedMap typedMap=TypedMap.fromMap(source);
  assertThat(typedMap.<String>getTyped("string")).isInstanceOf(String.class);
  assertThat(typedMap.<Integer>getTyped("int")).isInstanceOf(Integer.class);
}
