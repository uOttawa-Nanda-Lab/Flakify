@Test public void should_allow_assertions_on_field_values_extracted_from_given_iterable() throws Exception {
  assertThat(employees).extracting("id").as("extract field").containsOnly(1L,2L);
  assertThat(employees).extracting("surname").as("null field").containsNull();
  assertThat(employees).extracting("surname.first").as("null nested field").containsNull();
  yoda.surname=new Name();
  assertThat(employees).extracting("surname.first").as("not null field but null nested field").containsNull();
  yoda.surname=new Name("Master");
  assertThat(employees).extracting("surname.first").as("nested field").containsOnly("Master",null);
  assertThat(employees).extracting("surname",Name.class).as("extract field specifiying the extracted type").containsOnly(new Name("Master"),null);
}
