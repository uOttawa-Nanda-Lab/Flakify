@Test public void should_get_remove_ps_transformer() throws Exception {
  when(queryGenerator.prepareRemovePSs(session,entityMeta)).thenReturn(removePSs);
  Function<EntityMeta,Map<String,PreparedStatement>> function=builder.getRemovePSTransformer(session);
  ImmutableList<Map<String,PreparedStatement>> result=FluentIterable.from(Arrays.asList(entityMeta)).transform(function).toList();
  assertThat(result.get(0)).isSameAs(removePSs);
}
