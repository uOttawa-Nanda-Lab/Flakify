@Test public void should_create_Assert(){
  AbstractIntegerAssert<?> assertions=Assertions.assertThat(0);
  assertNotNull(assertions);
}
