@Test public void should_fail_if_no_fields_are_given(){
  thrown.expectIllegalArgumentException("No fields specified");
  new OnFieldsComparator();
}
