@Test public void should_create_Assert(){
  AbstractLongArrayAssert<?> assertions=Assertions.assertThat(emptyArray());
  assertNotNull(assertions);
}
