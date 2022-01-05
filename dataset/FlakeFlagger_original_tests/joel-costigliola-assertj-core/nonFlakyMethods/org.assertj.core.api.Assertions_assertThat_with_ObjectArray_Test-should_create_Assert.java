@Test public void should_create_Assert(){
  AbstractObjectArrayAssert<?,Object> assertions=Assertions.assertThat(emptyArray());
  assertNotNull(assertions);
}
