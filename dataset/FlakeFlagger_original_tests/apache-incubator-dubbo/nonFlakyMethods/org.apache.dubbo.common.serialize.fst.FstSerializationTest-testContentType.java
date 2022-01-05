@Test public void testContentType(){
  assertThat(fstSerialization.getContentType(),is("x-application/fst"));
}
