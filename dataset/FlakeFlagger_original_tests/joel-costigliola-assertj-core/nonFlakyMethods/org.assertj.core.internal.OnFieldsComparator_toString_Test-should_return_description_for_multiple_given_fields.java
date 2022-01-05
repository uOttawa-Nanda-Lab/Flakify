@Test public void should_return_description_for_multiple_given_fields(){
  assertThat(new OnFieldsComparator("a","b").toString()).isEqualTo("field by field comparator on fields [\"a\", \"b\"]");
}
