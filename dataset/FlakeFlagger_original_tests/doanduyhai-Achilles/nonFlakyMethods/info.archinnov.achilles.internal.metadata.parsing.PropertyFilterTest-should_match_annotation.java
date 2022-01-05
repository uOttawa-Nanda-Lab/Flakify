@Test public void should_match_annotation() throws Exception {
  Field friends=CompleteBean.class.getDeclaredField("friends");
  assertThat(filter.matches(friends,Column.class)).isTrue();
}
