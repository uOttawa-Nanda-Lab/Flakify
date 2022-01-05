@Test public void should_pass_actual(){
  long[] actual=emptyArray();
  AbstractLongArrayAssert<?> assertions=Assertions.assertThat(actual);
  assertSame(actual,assertions.actual);
}
