@Test public void should_fail_if_some_fields_are_null(){
  thrown.expectIllegalArgumentException("Null/blank fields are invalid, fields were [\"a\", null]");
  new OnFieldsComparator("a",null);
}
