@Test public void should_allow_assertions_on_property_values_extracted_from_given_iterable_with_extracted_type_defined() throws Exception {
  assertThat(employees).extracting("name",Name.class).usingElementComparator(new Comparator<Name>(){
    @Override public int compare(    Name o1,    Name o2){
      return o1.getFirst().compareTo(o2.getFirst());
    }
  }
).containsOnly(new Name("Yoda"),new Name("Luke","Skywalker"));
}
