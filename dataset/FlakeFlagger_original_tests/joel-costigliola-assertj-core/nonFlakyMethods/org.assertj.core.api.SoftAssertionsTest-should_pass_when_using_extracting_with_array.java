@Test public void should_pass_when_using_extracting_with_array(){
  Name[] namesAsArray=new Name[]{name("John","Doe"),name("Jane","Doe")};
  try (AutoCloseableSoftAssertions softly=new AutoCloseableSoftAssertions()){
    softly.assertThat(namesAsArray).extracting("first").as("using extracting()").contains("John").contains("Jane");
    softly.assertThat(namesAsArray).extracting(new Extractor<Name,String>(){
      @Override public String extract(      Name input){
        return input.getFirst();
      }
    }
).as("using extracting(Extractor)").contains("John").contains("Jane");
    softly.assertThat(namesAsArray).extracting("first",String.class).as("using extracting(..., Class)").contains("John").contains("Jane");
    softly.assertThat(namesAsArray).extracting("first","last").as("using extracting(...)").contains(tuple("John","Doe")).contains(tuple("Jane","Doe"));
    softly.assertThat(namesAsArray).extractingResultOf("getFirst",String.class).as("using extractingResultOf(method, Class)").contains("John").contains("Jane");
    softly.assertThat(namesAsArray).extractingResultOf("getFirst").as("using extractingResultOf(method)").contains("John").contains("Jane");
  }
 }
