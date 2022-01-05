@Test public void should_pass_actual(){
  AbstractFileAssert<?> assertions=Assertions.assertThat(actual);
  assertSame(actual,assertions.actual);
}
