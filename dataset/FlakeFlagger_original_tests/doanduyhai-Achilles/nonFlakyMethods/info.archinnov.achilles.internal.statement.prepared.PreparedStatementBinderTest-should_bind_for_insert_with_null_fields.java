@Test public void should_bind_for_insert_with_null_fields() throws Exception {
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").accessors().type(ID).transcoder(transcoder).invoker(invoker).build();
  PropertyMeta nameMeta=completeBean(Void.class,String.class).field("name").type(SIMPLE).accessors().transcoder(transcoder).invoker(invoker).build();
  PropertyMeta ageMeta=completeBean(Void.class,Long.class).field("age").type(SIMPLE).accessors().transcoder(transcoder).invoker(invoker).build();
  entityMeta.setIdMeta(idMeta);
  entityMeta.setAllMetasExceptIdAndCounters(asList(nameMeta,ageMeta));
  entityMeta.setClusteredCounter(false);
  long primaryKey=RandomUtils.nextLong();
  String name="name";
  when(invoker.getPrimaryKey(entity,idMeta)).thenReturn(primaryKey);
  when(invoker.getValueFromField(entity,nameMeta.getField())).thenReturn(name);
  when(invoker.getValueFromField(entity,ageMeta.getField())).thenReturn(null);
  when(transcoder.encode(idMeta,primaryKey)).thenReturn(primaryKey);
  when(transcoder.encode(nameMeta,name)).thenReturn(name);
  when(transcoder.encode(eq(ageMeta),any())).thenReturn(null);
  when(overrider.getWriteLevel(context)).thenReturn(ALL);
  when(ps.bind(Matchers.anyVararg())).thenReturn(bs);
  BoundStatementWrapper actual=binder.bindForInsert(context,ps,asList(nameMeta,ageMeta));
  verify(bs).setConsistencyLevel(ConsistencyLevel.ALL);
  assertThat(asList(actual.getValues())).containsExactly(primaryKey,name,null,0);
}
