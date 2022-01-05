@Test public void should_get_clustered_counter_ps_transformer() throws Exception {
  when(queryGenerator.prepareClusteredCounterQueryMap(session,entityMeta)).thenReturn(clusteredCounterQueryMap);
  Function<EntityMeta,Map<CQLQueryType,Map<String,PreparedStatement>>> function=builder.getClusteredCounterTransformer(session);
  ImmutableList<Map<CQLQueryType,Map<String,PreparedStatement>>> result=FluentIterable.from(Arrays.asList(entityMeta)).transform(function).toList();
  assertThat(result.get(0)).isSameAs(clusteredCounterQueryMap);
}
