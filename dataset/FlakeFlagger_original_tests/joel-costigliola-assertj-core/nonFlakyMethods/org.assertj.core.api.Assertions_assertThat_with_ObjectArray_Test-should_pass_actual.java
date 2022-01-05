@Test public void should_pass_actual(){
  Object[] actual=emptyArray();
  AbstractObjectArrayAssert<?,Object> assertions=Assertions.assertThat(actual);
  assertSame(actual,assertions.actual);
}
