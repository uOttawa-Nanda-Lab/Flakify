@Test public void should_create_Assert(){
  AbstractLongAssert<?> assertions=Assertions.assertThat(0L);
  assertNotNull(assertions);
}
