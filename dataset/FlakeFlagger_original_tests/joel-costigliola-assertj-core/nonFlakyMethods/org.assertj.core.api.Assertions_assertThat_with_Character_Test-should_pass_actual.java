@Test public void should_pass_actual(){
  Character b='b';
  AbstractCharacterAssert<?> assertions=Assertions.assertThat(b);
  assertSame(b,assertions.actual);
}
