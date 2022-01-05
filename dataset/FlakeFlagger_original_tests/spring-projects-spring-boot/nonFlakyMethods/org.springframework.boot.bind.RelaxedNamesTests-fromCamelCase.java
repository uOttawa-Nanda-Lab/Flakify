@Test public void fromCamelCase() throws Exception {
  Iterator<String> iterator=new RelaxedNames("caMel").iterator();
  assertThat(iterator.next(),equalTo("caMel"));
  assertThat(iterator.next(),equalTo("ca_mel"));
  assertThat(iterator.next(),equalTo("ca-mel"));
  assertThat(iterator.next(),equalTo("camel"));
  assertThat(iterator.next(),equalTo("CAMEL"));
  assertThat(iterator.next(),equalTo("CA_MEL"));
  assertThat(iterator.next(),equalTo("CA-MEL"));
  assertThat(iterator.hasNext(),equalTo(false));
}
