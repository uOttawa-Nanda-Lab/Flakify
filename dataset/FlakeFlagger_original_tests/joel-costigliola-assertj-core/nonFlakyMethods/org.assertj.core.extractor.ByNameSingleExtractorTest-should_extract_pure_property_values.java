@Test public void should_extract_pure_property_values(){
  Object extractedValues=adultExtractor().extract(yoda);
  assertThat(extractedValues).isEqualTo(true);
}
