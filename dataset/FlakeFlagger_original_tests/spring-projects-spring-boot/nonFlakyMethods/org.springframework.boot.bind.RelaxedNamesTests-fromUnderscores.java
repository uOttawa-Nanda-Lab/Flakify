@Test public void fromUnderscores() throws Exception {
  Iterator<String> iterator=new RelaxedNames("nes_ted").iterator();
  assertThat(iterator.next(),equalTo("nes_ted"));
  assertThat(iterator.next(),equalTo("nes.ted"));
  assertThat(iterator.next(),equalTo("nesTed"));
  assertThat(iterator.next(),equalTo("nested"));
  assertThat(iterator.next(),equalTo("NES_TED"));
  assertThat(iterator.next(),equalTo("NES.TED"));
  assertThat(iterator.next(),equalTo("NESTED"));
  assertThat(iterator.hasNext(),equalTo(false));
}
