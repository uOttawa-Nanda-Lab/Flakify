@Test public void testExceptionOnValidation(){
  this.context.register(ExceptionIfInvalidTestConfiguration.class);
  EnvironmentTestUtils.addEnvironment(this.context,"name:foo");
  this.expected.expectCause(Matchers.<Throwable>instanceOf(BindException.class));
  this.context.refresh();
}
