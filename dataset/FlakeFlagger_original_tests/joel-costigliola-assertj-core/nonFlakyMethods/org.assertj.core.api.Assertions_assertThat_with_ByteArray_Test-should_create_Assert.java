@Test public void should_create_Assert(){
  AbstractByteArrayAssert<?> assertions=Assertions.assertThat(emptyArray());
  assertNotNull(assertions);
}
