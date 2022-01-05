@Test public void should_create_Assert(){
  AbstractDoubleAssert<?> assertions=Assertions.assertThat(0d);
  assertNotNull(assertions);
}
