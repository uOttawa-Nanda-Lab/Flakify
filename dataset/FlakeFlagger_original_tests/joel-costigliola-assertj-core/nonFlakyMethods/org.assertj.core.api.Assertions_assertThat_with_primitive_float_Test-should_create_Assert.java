@Test public void should_create_Assert(){
  AbstractFloatAssert<?> assertions=Assertions.assertThat(0f);
  assertNotNull(assertions);
}
