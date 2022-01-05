@Test public void should_exception_when_no_id() throws Exception {
  CompleteBean bean=CompleteBeanTestBuilder.builder().id(12L).buid();
  when(proxifier.<CompleteBean>deriveBaseClass(bean)).thenReturn(CompleteBean.class);
  when(proxifier.getRealObject(bean)).thenReturn(bean);
  when(entityMetaMap.get(CompleteBean.class)).thenReturn(entityMeta);
  when(entityMeta.getPrimaryKey(bean)).thenReturn(null);
  exception.expect(IllegalArgumentException.class);
  exception.expectMessage("Cannot get primary key for entity " + CompleteBean.class.getCanonicalName());
  entityValidator.validateEntity(bean,entityMetaMap);
}
