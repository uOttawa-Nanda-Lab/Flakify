@Test public void fromPrefixEndingInPeriod() throws Exception {
  Iterator<String> iterator=new RelaxedNames("spring.").iterator();
  assertThat(iterator.next(),equalTo("spring."));
  assertThat(iterator.next(),equalTo("spring_"));
  assertThat(iterator.next(),equalTo("SPRING."));
  assertThat(iterator.next(),equalTo("SPRING_"));
  assertThat(iterator.hasNext(),equalTo(false));
}
