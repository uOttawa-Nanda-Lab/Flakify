@Test public void should_pass_actual(){
  boolean[] actual=emptyArray();
  AbstractBooleanArrayAssert<?> assertions=Assertions.assertThat(actual);
  assertSame(actual,assertions.actual);
}
