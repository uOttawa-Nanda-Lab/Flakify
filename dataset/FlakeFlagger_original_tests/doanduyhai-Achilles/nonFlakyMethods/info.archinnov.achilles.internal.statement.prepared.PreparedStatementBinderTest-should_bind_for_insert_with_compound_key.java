@Test public void should_bind_for_insert_with_compound_key() throws Exception {
  long userId=RandomUtils.nextLong();
  String name="name";
  List<Object> friends=Arrays.<Object>asList("foo","bar");
  Set<Object> followers=Sets.<Object>newHashSet("George","Paul");
  Map<Object,Object> preferences=ImmutableMap.<Object,Object>of(1,"FR");
  PropertyMeta idMeta=completeBean(Void.class,Long.class).field("id").accessors().type(EMBEDDED_ID).transcoder(transcoder).invoker(invoker).build();
  PropertyMeta friendsMeta=completeBean(Void.class,String.class).field("friends").type(LIST).transcoder(transcoder).accessors().invoker(invoker).build();
  PropertyMeta followersMeta=completeBean(Void.class,Long.class).field("followers").type(SET).transcoder(transcoder).accessors().invoker(invoker).build();
  PropertyMeta preferencesMeta=completeBean(Void.class,Long.class).field("preferences").type(MAP).transcoder(transcoder).accessors().invoker(invoker).build();
  entityMeta.setIdMeta(idMeta);
  entityMeta.setAllMetasExceptIdAndCounters(asList(friendsMeta,followersMeta,preferencesMeta));
  entityMeta.setClusteredCounter(false);
  EmbeddedKey embeddedKey=new EmbeddedKey(userId,name);
  when(invoker.getPrimaryKey(entity,idMeta)).thenReturn(embeddedKey);
  when(invoker.getValueFromField(entity,friendsMeta.getField())).thenReturn(friends);
  when(invoker.getValueFromField(entity,followersMeta.getField())).thenReturn(followers);
  when(invoker.getValueFromField(entity,preferencesMeta.getField())).thenReturn(preferences);
  when(overrider.getWriteLevel(context)).thenReturn(ALL);
  when(transcoder.encodeToComponents(idMeta,embeddedKey)).thenReturn(Arrays.<Object>asList(userId,name));
  when(transcoder.encode(friendsMeta,friends)).thenReturn(friends);
  when(transcoder.encode(followersMeta,followers)).thenReturn(followers);
  when(transcoder.encode(preferencesMeta,preferences)).thenReturn(preferences);
  when(ps.bind(Matchers.anyVararg())).thenReturn(bs);
  BoundStatementWrapper actual=binder.bindForInsert(context,ps,asList(friendsMeta,followersMeta,preferencesMeta));
  verify(bs).setConsistencyLevel(ConsistencyLevel.ALL);
  assertThat(asList(actual.getValues())).containsExactly(userId,name,friends,followers,preferences,0);
}
