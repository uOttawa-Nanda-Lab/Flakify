@Test public void should_eager_load_entity() throws Exception {
  Row row=mock(Row.class);
  when(daoContext.loadEntity(context.daoFacade)).thenReturn(row);
  assertThat(facade.loadEntity()).isSameAs(row);
}
