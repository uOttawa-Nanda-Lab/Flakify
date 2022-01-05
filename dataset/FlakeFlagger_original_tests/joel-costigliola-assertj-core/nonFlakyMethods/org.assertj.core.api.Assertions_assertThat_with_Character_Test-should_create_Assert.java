@Test public void should_create_Assert(){
  Character a='a';
  AbstractCharacterAssert<?> assertions=Assertions.assertThat(a);
  assertNotNull(assertions);
}
