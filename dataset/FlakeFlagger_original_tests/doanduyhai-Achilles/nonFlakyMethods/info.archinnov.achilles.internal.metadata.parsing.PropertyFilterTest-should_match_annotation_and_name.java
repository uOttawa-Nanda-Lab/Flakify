@Test public void should_match_annotation_and_name() throws Exception {
  Field friends=CompleteBean.class.getDeclaredField("friends");
  assertThat(filter.matches(friends,Column.class,"friends")).isTrue();
}
