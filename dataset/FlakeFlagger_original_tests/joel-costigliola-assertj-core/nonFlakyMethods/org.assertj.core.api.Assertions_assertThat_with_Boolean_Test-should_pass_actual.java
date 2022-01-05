@Test public void should_pass_actual(){
  AbstractBooleanAssert<?> assertions=Assertions.assertThat(TRUE);
  assertSame(TRUE,assertions.actual);
}
