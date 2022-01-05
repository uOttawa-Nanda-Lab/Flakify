@Test public void should_throw_exception_if_property_cannot_be_extracted_due_to_runtime_exception_during_property_access() throws Exception {
  thrown.expect(IntrospectionError.class);
  Employee employee=brokenEmployee();
  adultExtractor().extract(employee);
}
