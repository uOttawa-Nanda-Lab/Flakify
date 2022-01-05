@Test public void should_format_Map_containing_itself(){
  Map<String,Object> map=Maps.newHashMap();
  map.put("One","First");
  map.put("Myself",map);
  assertThat(Maps.format(standardRepresentation,map)).isEqualTo("{\"One\"=\"First\", \"Myself\"=(this Map)}");
  assertThat(Maps.format(map)).isEqualTo("{\"One\"=\"First\", \"Myself\"=(this Map)}");
}
