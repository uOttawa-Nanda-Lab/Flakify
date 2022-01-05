@Test public void should_have_annotation() throws Exception {
  Field name=CompleteBean.class.getDeclaredField("name");
  assertThat(filter.hasAnnotation(name,Column.class)).isTrue();
}
