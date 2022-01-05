@Test public void should_validate_simple_id() throws Exception {
  CompleteBean bean=CompleteBeanTestBuilder.builder().id(12L).buid();
  when(proxifier.getRealObject(bean)).thenReturn(bean);
  when(entityMeta.getPrimaryKey(bean)).thenReturn(12L);
  when(idMeta.isEmbeddedId()).thenReturn(false);
  entityValidator.validateEntity(bean,entityMeta);
}
