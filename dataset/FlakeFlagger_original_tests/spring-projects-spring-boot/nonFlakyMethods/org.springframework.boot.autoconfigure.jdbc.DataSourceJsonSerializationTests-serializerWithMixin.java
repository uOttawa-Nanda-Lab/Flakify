@Test public void serializerWithMixin() throws Exception {
  DataSource dataSource=new DataSource();
  ObjectMapper mapper=new ObjectMapper();
  mapper.addMixInAnnotations(DataSource.class,DataSourceJson.class);
  String value=mapper.writeValueAsString(dataSource);
  assertTrue(value.contains("\"url\":"));
  assertEquals(1,StringUtils.countOccurrencesOf(value,"\"url\""));
}
