@Test public void validationFailedRange(){
  validateJSR303(buildDto("regex","length",25));
  assertTrue(context.getValidation().hasViolations());
  assertEquals(context.getValidation().getBeanViolations().size(),1);
  assertTrue("Expected to have range violation.",context.getValidation().hasBeanViolation("range"));
  assertTrue(context.getValidation().getBeanViolations().get(0).field.contentEquals("range"));
}
