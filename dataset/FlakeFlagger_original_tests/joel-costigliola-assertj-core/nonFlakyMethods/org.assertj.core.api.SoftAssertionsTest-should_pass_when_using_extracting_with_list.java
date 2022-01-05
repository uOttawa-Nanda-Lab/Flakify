@Test public void should_pass_when_using_extracting_with_list(){
  List<Name> names=asList(name("John","Doe"),name("Jane","Doe"));
  softly.assertThat(names).extracting("first").as("using extracting()").contains("John").contains("Jane");
  softly.assertThat(names).extracting(new Extractor<Name,String>(){
    @Override public String extract(    Name input){
      return input.getFirst();
    }
  }
).as("using extracting(Extractor)").contains("John").contains("Jane");
  softly.assertThat(names).extracting("first",String.class).as("using extracting(..., Class)").contains("John").contains("Jane");
  softly.assertThat(names).extracting("first","last").as("using extracting(...)").contains(tuple("John","Doe")).contains(tuple("Jane","Doe"));
  softly.assertThat(names).extractingResultOf("getFirst",String.class).as("using extractingResultOf(method, Class)").contains("John").contains("Jane");
  softly.assertThat(names).extractingResultOf("getFirst").as("using extractingResultOf(method)").contains("John").contains("Jane");
  softly.assertAll();
}
