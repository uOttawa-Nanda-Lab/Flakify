@Test public void nameAndValueMustNotBeSpecified() throws Exception {
  this.thrown.expect(IllegalStateException.class);
  this.thrown.expectCause(hasMessage(containsString("The name and " + "value attributes of @ConditionalOnProperty are exclusive")));
  load(NameAndValueAttribute.class,"some.property");
}
