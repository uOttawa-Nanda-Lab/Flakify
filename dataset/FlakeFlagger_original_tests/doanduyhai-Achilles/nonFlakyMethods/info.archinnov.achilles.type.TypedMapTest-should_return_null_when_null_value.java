@Test public void should_return_null_when_null_value() throws Exception {
  TypedMap map=new TypedMap();
  map.put("key",null);
  Counter counter=map.<Counter>getTyped("key");
  assertThat(counter).isNull();
}
