@Test public void should_return_empty_braces_if_Map_is_empty(){
  assertThat(Maps.format(standardRepresentation,new HashMap<String,String>())).isEqualTo("{}");
  assertThat(Maps.format(new HashMap<String,String>())).isEqualTo("{}");
}
