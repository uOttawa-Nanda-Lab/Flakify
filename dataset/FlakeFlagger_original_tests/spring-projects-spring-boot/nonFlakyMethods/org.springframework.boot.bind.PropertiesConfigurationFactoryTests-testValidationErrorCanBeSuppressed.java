@Test public void testValidationErrorCanBeSuppressed() throws Exception {
  this.validator=new SpringValidatorAdapter(Validation.buildDefaultValidatorFactory().getValidator());
  setupFactory();
  this.factory.setExceptionIfInvalid(false);
  bindFoo("bar: blah");
}
