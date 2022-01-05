@Test public void validationPassed(){
  validateJSR303(buildDto("regex","length",5));
  assertFalse(context.getValidation().hasViolations());
  assertFalse("Expected not to have regex violation.",context.getValidation().hasBeanViolation("regex"));
}
