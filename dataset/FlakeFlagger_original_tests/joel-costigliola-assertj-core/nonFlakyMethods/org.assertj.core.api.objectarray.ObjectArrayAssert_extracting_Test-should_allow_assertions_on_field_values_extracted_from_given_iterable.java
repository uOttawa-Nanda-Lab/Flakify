@Test public void should_allow_assertions_on_field_values_extracted_from_given_iterable() throws Exception {
  assertThat(employees).extracting("id").containsOnly(1L,2L);
  assertThat(employees).extracting("name").containsOnly(new Name("Yoda"),new Name("Luke","Skywalker"));
  assertThat(employees).extracting("name.first").containsOnly("Yoda","Luke");
}
