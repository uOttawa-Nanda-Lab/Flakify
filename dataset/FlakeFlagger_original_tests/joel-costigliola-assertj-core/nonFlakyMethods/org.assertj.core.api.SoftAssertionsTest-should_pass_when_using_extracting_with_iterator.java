@Test public void should_pass_when_using_extracting_with_iterator(){
  Iterator<Name> names=asList(name("John","Doe"),name("Jane","Doe")).iterator();
  try (AutoCloseableSoftAssertions softly=new AutoCloseableSoftAssertions()){
    softly.assertThat(names).extracting("first").as("using extracting()").contains("John").contains("Jane");
  }
 }
