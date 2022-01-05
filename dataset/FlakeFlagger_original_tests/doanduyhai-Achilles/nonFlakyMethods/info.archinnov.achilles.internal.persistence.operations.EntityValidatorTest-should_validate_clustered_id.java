@Test public void should_validate_clustered_id() throws Exception {
  CompleteBean bean=CompleteBeanTestBuilder.builder().id(12L).buid();
  EmbeddedKey clusteredId=new EmbeddedKey(11L,"name");
  when(entityMeta.getPrimaryKey(bean)).thenReturn(clusteredId);
  when(proxifier.getRealObject(bean)).thenReturn(bean);
  when(idMeta.isEmbeddedId()).thenReturn(true);
  when(idMeta.encodeToComponents(clusteredId)).thenReturn(Arrays.<Object>asList(11L,"name"));
  entityValidator.validateEntity(bean,entityMeta);
}
