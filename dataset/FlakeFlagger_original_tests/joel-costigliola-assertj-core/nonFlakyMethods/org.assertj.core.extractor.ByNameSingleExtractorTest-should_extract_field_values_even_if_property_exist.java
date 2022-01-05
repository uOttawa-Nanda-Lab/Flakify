@Test public void should_extract_field_values_even_if_property_exist(){
  Object extractedValues=idExtractor().extract(yoda);
  assertThat(extractedValues).isEqualTo(1L);
}
