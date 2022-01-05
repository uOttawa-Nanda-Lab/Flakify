@Test public void should_extract_property_values_when_no_public_field_match_given_name(){
  Object extractedValues=ageExtractor().extract(yoda);
  assertThat(extractedValues).isEqualTo(800);
}
