@Test public void should_extract_tuples_from_fields_or_properties(){
  Extractor<Employee,Tuple> extractor=new ByNameMultipleExtractor<>("id","age");
  Tuple extractedValue=extractor.extract(yoda);
  assertThat(extractedValue).isEqualTo(tuple(1L,800));
}
