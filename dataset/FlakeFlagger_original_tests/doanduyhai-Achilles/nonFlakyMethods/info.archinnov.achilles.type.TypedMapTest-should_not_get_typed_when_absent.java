@Test public void should_not_get_typed_when_absent() throws Exception {
  TypedMap map=new TypedMap();
  Counter counter=map.<Counter>getTyped("key");
  assertThat(counter).isNull();
}
