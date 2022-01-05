@Test public void should_fail_if_empty_array_fields_is_given(){
  thrown.expectIllegalArgumentException("No fields specified");
  new OnFieldsComparator(new String[0]);
}
