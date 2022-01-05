@Test(expected=BindException.class) public void missingPropertyCausesValidationError() throws Exception {
  this.validator=new SpringValidatorAdapter(Validation.buildDefaultValidatorFactory().getValidator());
  createFoo("bar: blah");
}
