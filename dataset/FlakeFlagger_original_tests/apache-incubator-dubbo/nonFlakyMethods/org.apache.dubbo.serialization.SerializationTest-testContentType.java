@Test public void testContentType(){
  assertThat(mySerialization.getContentType(),is("x-application/my"));
}
