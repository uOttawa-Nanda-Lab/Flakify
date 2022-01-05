@Test public void validationFailedWithThreeFields(){
  validateJSR303(buildDto("regex!!!","length is now tooooo loooong",25));
  assertTrue(context.getValidation().hasViolations());
  assertTrue(context.getValidation().hasBeanViolations());
  assertTrue("Expected to have regex violation.",context.getValidation().hasBeanViolation("regex"));
  assertEquals(context.getValidation().getBeanViolations().size(),3);
  for (int i=0; i < context.getValidation().getBeanViolations().size(); i++) {
    String fieldName=context.getValidation().getBeanViolations().get(i).field;
    assertTrue(fieldName.contentEquals("regex") || fieldName.contentEquals("length") || fieldName.contentEquals("range"));
  }
}
