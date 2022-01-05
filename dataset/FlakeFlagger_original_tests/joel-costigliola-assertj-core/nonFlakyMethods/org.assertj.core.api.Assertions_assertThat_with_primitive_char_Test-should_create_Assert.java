@Test public void should_create_Assert(){
  AbstractCharacterAssert<?> assertions=Assertions.assertThat('a');
  assertNotNull(assertions);
}
