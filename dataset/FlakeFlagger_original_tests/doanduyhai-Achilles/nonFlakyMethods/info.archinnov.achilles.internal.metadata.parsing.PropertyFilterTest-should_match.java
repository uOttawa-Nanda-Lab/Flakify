@Test public void should_match() throws Exception {
  Field name=CompleteBean.class.getDeclaredField("name");
  assertThat(filter.matches(name)).isTrue();
}
