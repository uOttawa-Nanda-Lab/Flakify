@Test public void validationFailedWithTwoAnnotations(){
  validateJSR303(buildDto("regex!!! which is also too long","length",5));
  assertTrue(context.getValidation().hasViolations());
  assertTrue(context.getValidation().hasBeanViolations());
  assertEquals(context.getValidation().getBeanViolations().size(),2);
  for (int i=0; i < context.getValidation().getBeanViolations().size(); i++) {
    String fieldName=context.getValidation().getBeanViolations().get(i).field;
    assertTrue(fieldName.contentEquals("regex"));
  }
  String message0=context.getValidation().getBeanViolations().get(0).constraintViolation.getMessageKey();
  String message1=context.getValidation().getBeanViolations().get(1).constraintViolation.getMessageKey();
  assertFalse(message0.contentEquals(message1));
}
