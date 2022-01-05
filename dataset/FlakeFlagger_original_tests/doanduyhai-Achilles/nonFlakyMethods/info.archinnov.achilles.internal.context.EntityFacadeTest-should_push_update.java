@Test public void should_push_update() throws Exception {
  List<PropertyMeta> pms=Arrays.asList();
  facade.pushUpdateStatement(pms);
  verify(daoContext).pushUpdateStatement(context.daoFacade,pms);
}
