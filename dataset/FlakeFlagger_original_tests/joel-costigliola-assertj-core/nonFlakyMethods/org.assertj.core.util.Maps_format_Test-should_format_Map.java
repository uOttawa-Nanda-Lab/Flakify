@Test public void should_format_Map(){
  Map<String,Class<?>> map=new LinkedHashMap<>();
  map.put("One",String.class);
  map.put("Two",File.class);
  assertThat(Maps.format(standardRepresentation,map)).isEqualTo("{\"One\"=java.lang.String, \"Two\"=java.io.File}");
  assertThat(Maps.format(map)).isEqualTo("{\"One\"=java.lang.String, \"Two\"=java.io.File}");
}
