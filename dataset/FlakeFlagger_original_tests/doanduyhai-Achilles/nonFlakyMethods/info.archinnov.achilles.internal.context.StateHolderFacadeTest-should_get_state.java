@Test public void should_get_state() throws Exception {
  PropertyMeta nameMeta=PropertyMetaTestBuilder.completeBean(Void.class,String.class).field("name").accessors().build();
  PropertyMeta counterMeta=PropertyMetaTestBuilder.completeBean(Void.class,Counter.class).field("count").accessors().build();
  when(meta.isClusteredCounter()).thenReturn(true);
  when(meta.getAllCounterMetas()).thenReturn(asList(counterMeta));
  when(meta.getAllMetasExceptCounters()).thenReturn(asList(nameMeta));
  Options.CASCondition casCondition=new Options.CASCondition("test","test");
  CASResultListener listener=mock(CASResultListener.class);
  final Options options=OptionsBuilder.withConsistency(LOCAL_ONE).withTimestamp(100L).withTtl(9).ifConditions(casCondition).casResultListener(listener);
  context.options=options;
  context.entity=entity;
  assertThat(facade.getEntity()).isSameAs(entity);
  assertThat(facade.<CompleteBean>getEntityClass()).isSameAs(CompleteBean.class);
  assertThat(facade.getEntityMeta()).isSameAs(meta);
  assertThat(facade.getIdMeta()).isSameAs(idMeta);
  assertThat(facade.getPrimaryKey()).isSameAs(primaryKey);
  assertThat(facade.isClusteredCounter()).isTrue();
  assertThat(facade.getOptions()).isSameAs(options);
  assertThat(facade.getTtl().get()).isEqualTo(9);
  assertThat(facade.getTimestamp().get()).isEqualTo(100L);
  assertThat(facade.getConsistencyLevel().get()).isEqualTo(LOCAL_ONE);
  assertThat(facade.hasCasConditions()).isTrue();
  assertThat(facade.getCasConditions()).contains(casCondition);
  assertThat(facade.getCASResultListener().get()).isSameAs(listener);
  assertThat(facade.getAllCountersMeta()).containsExactly(counterMeta);
  assertThat(facade.getAllGettersExceptCounters()).containsExactly(nameMeta.getGetter());
}
