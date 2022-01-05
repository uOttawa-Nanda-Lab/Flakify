@Test public void testContentType(){
  assertThat(fastJsonSerialization.getContentType(),is("text/json"));
}
