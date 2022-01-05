@Test public void should_throw_error_if_field_is_not_public_and_allowExtractingPrivateFields_set_to_false(){
  FieldSupport.EXTRACTION.setAllowUsingPrivateFields(false);
  try {
    thrown.expect(IntrospectionError.class,"Unable to obtain the value of the field <'age'> from <Employee[id=1, name=Name[first='Yoda', last='null'], age=800]>, check that field is public.");
    fieldSupport.fieldValues("age",Integer.class,employees);
  }
  finally {
    FieldSupport.EXTRACTION.setAllowUsingPrivateFields(true);
  }
}
