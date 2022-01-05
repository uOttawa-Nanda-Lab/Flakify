@Test public void should_push_insert() throws Exception {
  EntityMeta meta=new EntityMeta();
  List<PropertyMeta> pms=new ArrayList<>();
  meta.setAllMetasExceptIdAndCounters(pms);
  context.entityMeta=meta;
  when(configurationContext.getInsertStrategy()).thenReturn(ALL_FIELDS);
  facade.pushInsertStatement();
  verify(daoContext).pushInsertStatement(context.daoFacade,pms);
}
