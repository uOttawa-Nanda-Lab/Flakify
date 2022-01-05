@Test public void testRequiredFieldsValidation() throws Exception {
  TargetWithValidatedMap target=new TargetWithValidatedMap();
  BindingResult result=bind(target,"info[foo]: bar");
  assertEquals(2,result.getErrorCount());
  for (  FieldError error : result.getFieldErrors()) {
    System.err.println(new StaticMessageSource().getMessage(error,Locale.getDefault()));
  }
}
