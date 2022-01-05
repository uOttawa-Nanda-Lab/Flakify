@Test public void testDefaultFactory(){
  FstFactory factory=FstFactory.getDefaultFactory();
  assertThat(factory,not(nullValue()));
}
