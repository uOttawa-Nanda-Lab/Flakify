@Test public void should_pass_actual(){
  AbstractCharacterAssert<?> assertions=Assertions.assertThat('b');
  assertEquals(new Character('b'),assertions.actual);
}
