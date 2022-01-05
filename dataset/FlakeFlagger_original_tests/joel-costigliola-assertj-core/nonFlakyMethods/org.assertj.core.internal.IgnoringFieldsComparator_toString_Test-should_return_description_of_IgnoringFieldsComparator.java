@Test public void should_return_description_of_IgnoringFieldsComparator(){
  assertThat(new IgnoringFieldsComparator("a","b").toString()).isEqualTo("field by field comparator on all fields except [\"a\", \"b\"]");
}
