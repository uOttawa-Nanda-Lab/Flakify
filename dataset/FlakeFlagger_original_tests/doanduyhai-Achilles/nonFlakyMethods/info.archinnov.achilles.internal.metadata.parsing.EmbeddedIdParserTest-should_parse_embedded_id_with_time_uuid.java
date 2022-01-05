@Test public void should_parse_embedded_id_with_time_uuid() throws Exception {
  EmbeddedIdProperties props=parser.parseEmbeddedId(EmbeddedKeyWithTimeUUID.class);
  assertThat(props.getTimeUUIDComponents()).containsExactly("date");
  assertThat(props.getComponentNames()).containsExactly("date","ranking");
}
