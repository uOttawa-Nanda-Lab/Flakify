@Test public void should_create_Assert(){
  AbstractShortAssert<?> assertions=Assertions.assertThat((short)8);
  assertNotNull(assertions);
}
