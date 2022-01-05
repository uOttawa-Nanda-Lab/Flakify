@Test public void should_extract_tuples_with_consistent_iteration_order(){
  Extractor<Employee,Tuple> extractor=new ByNameMultipleExtractor<>("id","name.first","age");
  Tuple extractedValues=extractor.extract(yoda);
  assertThat(extractedValues).isEqualTo(tuple(1L,"Yoda",800));
}
