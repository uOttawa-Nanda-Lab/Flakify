@Test public void should_return_description_for_a_single_given_field(){
  assertThat(new OnFieldsComparator("a").toString()).isEqualTo("single field comparator on field \"a\"");
}
