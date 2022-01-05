@Test public void should_not_have_annotation() throws Exception {
  Field name=CompleteBean.class.getDeclaredField("name");
  assertThat(filter.hasAnnotation(name,Id.class)).isFalse();
}
