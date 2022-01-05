@Test public void sohuld_allow_extracting_multiple_values_using_extractor() throws Exception {
  assertThat(employees).extracting(new Extractor<Employee,Tuple>(){
    @Override public Tuple extract(    Employee input){
      return new Tuple(input.getName().getFirst(),input.getAge(),input.id);
    }
  }
).containsOnly(tuple("Yoda",800,1L),tuple("Luke",26,2L));
}
