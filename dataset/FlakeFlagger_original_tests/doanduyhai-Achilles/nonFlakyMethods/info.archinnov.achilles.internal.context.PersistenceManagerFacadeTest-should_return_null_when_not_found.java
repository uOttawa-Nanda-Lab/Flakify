@Test public void should_return_null_when_not_found() throws Exception {
  when(loader.load(context.entityFacade,CompleteBean.class)).thenReturn(null);
  CompleteBean found=facade.find(CompleteBean.class);
  assertThat(found).isNull();
  verifyZeroInteractions(proxifier);
}
