@Test public void should_allow_assertions_on_null_property_values_extracted_from_given_iterable() throws Exception {
  yoda.name.setFirst(null);
  assertThat(employees).extracting("name.first").as("not null property but null nested property").containsOnly(null,"Luke");
  yoda.setName(null);
  assertThat(employees).extracting("name.first").as("extract nested property when top property is null").containsOnly(null,"Luke");
  assertThat(employees).extracting("name").as("null property").containsOnly(null,new Name("Luke","Skywalker"));
}
