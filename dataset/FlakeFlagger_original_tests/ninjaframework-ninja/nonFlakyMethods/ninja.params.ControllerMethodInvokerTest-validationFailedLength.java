@Test public void validationFailedLength(){
  validateJSR303(buildDto("regex","length - too long",5));
  assertTrue(context.getValidation().hasViolations());
  assertEquals(context.getValidation().getBeanViolations().size(),1);
  assertTrue("Expected to have length violation.",context.getValidation().hasBeanViolation("length"));
  assertTrue(context.getValidation().getBeanViolations().get(0).field.contentEquals("length"));
}
