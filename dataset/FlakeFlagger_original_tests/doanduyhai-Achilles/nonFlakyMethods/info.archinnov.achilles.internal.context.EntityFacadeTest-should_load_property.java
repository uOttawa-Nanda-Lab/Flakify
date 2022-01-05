@Test public void should_load_property() throws Exception {
  Row row=mock(Row.class);
  when(daoContext.loadProperty(context.daoFacade,idMeta)).thenReturn(row);
  assertThat(facade.loadProperty(idMeta)).isSameAs(row);
}
