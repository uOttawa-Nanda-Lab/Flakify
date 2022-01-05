@Test public void should_fail_if_null_array_fields_is_given(){
  thrown.expectIllegalArgumentException("No fields specified");
  new OnFieldsComparator((String[])null);
}
