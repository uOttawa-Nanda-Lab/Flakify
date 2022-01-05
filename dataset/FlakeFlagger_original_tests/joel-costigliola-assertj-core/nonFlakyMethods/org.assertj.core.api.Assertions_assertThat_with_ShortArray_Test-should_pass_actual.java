@Test public void should_pass_actual(){
  short[] actual=emptyArray();
  AbstractShortArrayAssert<?> assertions=Assertions.assertThat(actual);
  assertSame(actual,assertions.actual);
}
