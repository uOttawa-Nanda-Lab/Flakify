@Test public void should_create_empty_entity() throws Exception {
  when(meta.instanciate()).thenReturn(entity);
  CompleteBean actual=loader.createEmptyEntity(context,CompleteBean.class);
  assertThat(actual).isSameAs(entity);
  verify(idMeta).setValueToField(actual,primaryKey);
}
