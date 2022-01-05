@Test public void should_build_dao_context_with_counter() throws Exception {
  Map<Class<?>,EntityMeta> entityMetaMap=new HashMap<>();
  EntityMeta meta=new EntityMeta();
  PropertyMeta nameMeta=completeBean(Void.class,String.class).field("name").type(SIMPLE).build();
  meta.setPropertyMetas(ImmutableMap.of("name",nameMeta));
  entityMetaMap.put(CompleteBean.class,meta);
  when(queryGenerator.prepareSelectAll(session,meta)).thenReturn(selectEagerPS);
  when(queryGenerator.prepareRemovePSs(session,meta)).thenReturn(removePSs);
  when(queryGenerator.prepareSimpleCounterQueryMap(session)).thenReturn(counterQueryMap);
  when(parsingResult.getMetaMap()).thenReturn(entityMetaMap);
  when(parsingResult.hasSimpleCounter()).thenReturn(true);
  when(configContext.getPreparedStatementLRUCacheSize()).thenReturn(100);
  DaoContext actual=builder.create(session,parsingResult,configContext);
  assertThat(Whitebox.<Map<Class<?>,PreparedStatement>>getInternalState(actual,"selectPSs")).containsValue(selectEagerPS);
  assertThat(Whitebox.<Map<Class<?>,Map<String,PreparedStatement>>>getInternalState(actual,"removePSs")).containsKey(CompleteBean.class);
  assertThat(Whitebox.<Cache<StatementCacheKey,PreparedStatement>>getInternalState(actual,"dynamicPSCache")).isInstanceOf(Cache.class);
  assertThat(Whitebox.<Map<CQLQueryType,PreparedStatement>>getInternalState(actual,"counterQueryMap")).isSameAs(counterQueryMap);
}
