@Test public void should_pass_actual(){
  float[] actual=emptyArray();
  AbstractFloatArrayAssert<?> assertions=Assertions.assertThat(actual);
  assertSame(actual,assertions.actual);
}
