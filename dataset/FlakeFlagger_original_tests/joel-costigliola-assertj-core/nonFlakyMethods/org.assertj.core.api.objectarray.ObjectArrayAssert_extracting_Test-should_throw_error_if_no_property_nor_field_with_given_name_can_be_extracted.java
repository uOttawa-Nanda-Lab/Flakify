@Test public void should_throw_error_if_no_property_nor_field_with_given_name_can_be_extracted() throws Exception {
  thrown.expect(IntrospectionError.class);
  assertThat(employees).extracting("unknown");
}
