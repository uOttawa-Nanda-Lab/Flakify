@Test public void should_get_select_eager_ps_transformer() throws Exception {
  when(queryGenerator.prepareSelectAll(session,entityMeta)).thenReturn(selectEagerPS);
  Function<EntityMeta,PreparedStatement> function=builder.getSelectPSTransformer(session);
  ImmutableList<PreparedStatement> result=FluentIterable.from(Arrays.asList(entityMeta)).transform(function).toList();
  assertThat(result).containsOnly(selectEagerPS);
}
