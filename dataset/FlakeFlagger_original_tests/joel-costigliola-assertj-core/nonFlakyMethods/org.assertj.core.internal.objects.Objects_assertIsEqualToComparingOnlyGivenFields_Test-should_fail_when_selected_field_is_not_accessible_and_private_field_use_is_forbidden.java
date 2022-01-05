@Test public void should_fail_when_selected_field_is_not_accessible_and_private_field_use_is_forbidden(){
  boolean allowedToUsePrivateFields=FieldSupport.comparison().isAllowedToUsePrivateFields();
  Assertions.setAllowComparingPrivateFields(false);
  thrown.expect(IntrospectionError.class,"Unable to obtain the value of <'strangeNotReadablePrivateField'> field/property from <Yoda the Jedi>, expecting a public field or getter");
  Jedi actual=new Jedi("Yoda","Green");
  Jedi other=new Jedi("Yoda","Blue");
  objects.assertIsEqualToComparingOnlyGivenFields(someInfo(),actual,other,"strangeNotReadablePrivateField");
  Assertions.setAllowComparingPrivateFields(allowedToUsePrivateFields);
}
