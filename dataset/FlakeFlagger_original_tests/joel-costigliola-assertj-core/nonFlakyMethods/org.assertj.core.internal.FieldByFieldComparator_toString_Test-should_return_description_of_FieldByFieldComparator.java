@Test public void should_return_description_of_FieldByFieldComparator(){
  assertThat(new FieldByFieldComparator().toString()).isEqualTo("field by field comparator on all fields");
}
