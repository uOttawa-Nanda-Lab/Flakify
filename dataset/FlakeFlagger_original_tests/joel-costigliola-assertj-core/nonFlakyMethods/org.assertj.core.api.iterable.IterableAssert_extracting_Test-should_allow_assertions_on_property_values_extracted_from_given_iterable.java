@Test public void should_allow_assertions_on_property_values_extracted_from_given_iterable() throws Exception {
  assertThat(employees).extracting("age").as("extract property backed by a private field").containsOnly(800,26);
  assertThat(employees).extracting("adult").as("extract pure property").containsOnly(true,true);
  assertThat(employees).extracting("name.first").as("nested property").containsOnly("Yoda","Luke");
  assertThat(employees).extracting("name").as("extract field that is also a property").containsOnly(new Name("Yoda"),new Name("Luke","Skywalker"));
  assertThat(employees).extracting("name",Name.class).as("extract field that is also a property but specifiying the extracted type").containsOnly(new Name("Yoda"),new Name("Luke","Skywalker"));
}
