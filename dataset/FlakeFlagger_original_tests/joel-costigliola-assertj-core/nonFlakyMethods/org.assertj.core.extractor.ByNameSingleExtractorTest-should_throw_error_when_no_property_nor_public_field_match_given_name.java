@Test public void should_throw_error_when_no_property_nor_public_field_match_given_name(){
  thrown.expect(IntrospectionError.class);
  new ByNameSingleExtractor<Employee>("unknown").extract(yoda);
}
