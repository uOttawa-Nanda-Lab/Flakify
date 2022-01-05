@Test public void should_prefer_properties_over_fields() throws Exception {
  Object extractedValue=nameExtractor().extract(employeeWithOverriddenName("Overridden Name"));
  assertThat(extractedValue).isEqualTo(new Name("Overridden Name"));
}
