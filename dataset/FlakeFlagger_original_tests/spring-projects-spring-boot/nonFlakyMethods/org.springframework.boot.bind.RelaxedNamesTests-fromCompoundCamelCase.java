@Test public void fromCompoundCamelCase() throws Exception {
  Iterator<String> iterator=new RelaxedNames("caMelCase").iterator();
  assertThat(iterator.next(),equalTo("caMelCase"));
  assertThat(iterator.next(),equalTo("ca_mel_case"));
  assertThat(iterator.next(),equalTo("ca-mel-case"));
  assertThat(iterator.next(),equalTo("camelcase"));
  assertThat(iterator.next(),equalTo("CAMELCASE"));
  assertThat(iterator.next(),equalTo("CA_MEL_CASE"));
  assertThat(iterator.next(),equalTo("CA-MEL-CASE"));
  assertThat(iterator.hasNext(),equalTo(false));
}
