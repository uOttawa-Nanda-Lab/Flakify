@Test public void should_pass_actual(){
  int[] actual=emptyArray();
  AbstractIntArrayAssert<?> assertions=Assertions.assertThat(actual);
  assertSame(actual,assertions.actual);
}
