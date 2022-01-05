@Test public void should_validate() throws Exception {
  CompleteBean bean=CompleteBeanTestBuilder.builder().id(12L).buid();
  when(proxifier.<CompleteBean>deriveBaseClass(bean)).thenReturn(CompleteBean.class);
  when(proxifier.getRealObject(bean)).thenReturn(bean);
  when(entityMetaMap.get(CompleteBean.class)).thenReturn(entityMeta);
  when(entityMeta.getPrimaryKey(bean)).thenReturn(12L);
  entityValidator.validateEntity(bean,entityMetaMap);
}
