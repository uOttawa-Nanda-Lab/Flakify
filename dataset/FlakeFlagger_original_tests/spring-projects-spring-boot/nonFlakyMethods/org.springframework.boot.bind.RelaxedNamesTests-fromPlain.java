@Test public void fromPlain() throws Exception {
  Iterator<String> iterator=new RelaxedNames("plain").iterator();
  assertThat(iterator.next(),equalTo("plain"));
  assertThat(iterator.next(),equalTo("PLAIN"));
  assertThat(iterator.hasNext(),equalTo(false));
}
