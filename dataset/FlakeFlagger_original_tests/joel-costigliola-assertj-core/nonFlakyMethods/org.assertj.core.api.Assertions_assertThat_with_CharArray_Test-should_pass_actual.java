@Test public void should_pass_actual(){
  char[] actual=emptyArray();
  AbstractCharArrayAssert<?> assertions=Assertions.assertThat(actual);
  assertSame(actual,assertions.actual);
}
