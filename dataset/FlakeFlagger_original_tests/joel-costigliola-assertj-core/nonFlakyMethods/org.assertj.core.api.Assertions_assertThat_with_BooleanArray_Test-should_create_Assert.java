@Test public void should_create_Assert(){
  AbstractBooleanArrayAssert<?> assertions=Assertions.assertThat(emptyArray());
  assertNotNull(assertions);
}
