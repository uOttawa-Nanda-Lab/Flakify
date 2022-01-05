@Test public void should_derive_base_class_from_transient() throws Exception {
  assertThat(proxifier.<CompleteBean>deriveBaseClass(new CompleteBean())).isEqualTo(CompleteBean.class);
}
