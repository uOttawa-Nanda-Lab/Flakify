@Test(expected=BindException.class) public void testMissingPropertyCausesValidationError() throws Exception {
  this.validator=new SpringValidatorAdapter(Validation.buildDefaultValidatorFactory().getValidator());
  createFoo("bar: blah");
}
