@Test public void should_fail_if_some_fields_are_empty(){
  thrown.expectIllegalArgumentException("Null/blank fields are invalid, fields were [\"a\", \"\"]");
  new OnFieldsComparator("a","");
}
