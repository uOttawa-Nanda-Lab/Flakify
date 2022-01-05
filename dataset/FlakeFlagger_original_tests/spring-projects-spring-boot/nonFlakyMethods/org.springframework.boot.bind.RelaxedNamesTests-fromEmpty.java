@Test public void fromEmpty() throws Exception {
  Iterator<String> iterator=new RelaxedNames("").iterator();
  assertThat(iterator.next(),equalTo(""));
  assertThat(iterator.hasNext(),equalTo(false));
}
