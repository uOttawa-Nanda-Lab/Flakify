@Test public void testContentTypeId(){
  assertThat(fastJsonSerialization.getContentTypeId(),is((byte)6));
}
