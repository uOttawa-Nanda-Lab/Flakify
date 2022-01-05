@Test public void should_push_for_collection_and_map_update() throws Exception {
  facade.pushCollectionAndMapUpdateStatements(changeSet);
  verify(daoContext).pushCollectionAndMapUpdateStatement(context.daoFacade,changeSet);
}
