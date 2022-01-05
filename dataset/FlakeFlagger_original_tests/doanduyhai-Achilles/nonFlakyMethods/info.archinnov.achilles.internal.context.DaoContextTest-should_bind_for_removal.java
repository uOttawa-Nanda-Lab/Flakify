@Test public void should_bind_for_removal() throws Exception {
  when(context.<CompleteBean>getEntityClass()).thenReturn(CompleteBean.class);
  when(removePSs.get(CompleteBean.class)).thenReturn(of("table",ps));
  when(overrider.getWriteLevel(context)).thenReturn(EACH_QUORUM);
  when(binder.bindStatementWithOnlyPKInWhereClause(context,ps,EACH_QUORUM)).thenReturn(bsWrapper);
  daoContext.bindForRemoval(context,"table");
  verify(context).pushStatement(bsWrapper);
}
