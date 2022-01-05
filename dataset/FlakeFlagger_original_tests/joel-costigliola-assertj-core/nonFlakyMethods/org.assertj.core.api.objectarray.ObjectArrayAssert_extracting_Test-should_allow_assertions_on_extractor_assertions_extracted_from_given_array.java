@Test public void should_allow_assertions_on_extractor_assertions_extracted_from_given_array() throws Exception {
  assertThat(employees).extracting(new Extractor<Employee,String>(){
    @Override public String extract(    Employee input){
      return input.getName().getFirst();
    }
  }
).containsOnly("Yoda","Luke");
}
