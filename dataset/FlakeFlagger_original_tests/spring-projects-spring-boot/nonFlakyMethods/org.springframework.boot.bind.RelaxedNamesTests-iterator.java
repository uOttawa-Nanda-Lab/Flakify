@Test public void iterator() throws Exception {
  Iterator<String> iterator=new RelaxedNames("my-RELAXED-property").iterator();
  assertThat(iterator.next(),equalTo("my-RELAXED-property"));
  assertThat(iterator.next(),equalTo("my_RELAXED_property"));
  assertThat(iterator.next(),equalTo("myRELAXEDProperty"));
  assertThat(iterator.next(),equalTo("my-relaxed-property"));
  assertThat(iterator.next(),equalTo("my_relaxed_property"));
  assertThat(iterator.next(),equalTo("myrelaxedproperty"));
  assertThat(iterator.next(),equalTo("MY-RELAXED-PROPERTY"));
  assertThat(iterator.next(),equalTo("MY_RELAXED_PROPERTY"));
  assertThat(iterator.next(),equalTo("MYRELAXEDPROPERTY"));
  assertThat(iterator.hasNext(),equalTo(false));
}
