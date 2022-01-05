@Test public void nameOrValueMustBeSpecified() throws Exception {
  this.thrown.expect(IllegalStateException.class);
  this.thrown.expectCause(hasMessage(containsString("The name or " + "value attribute of @ConditionalOnProperty must be specified")));
  load(NoNameOrValueAttribute.class,"some.property");
}
