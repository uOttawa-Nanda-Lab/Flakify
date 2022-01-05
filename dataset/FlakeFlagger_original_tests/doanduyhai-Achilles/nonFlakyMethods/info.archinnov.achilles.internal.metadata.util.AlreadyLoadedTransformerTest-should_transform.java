@Test public void should_transform() throws Exception {
  Map<Method,PropertyMeta> getterMetas=new HashMap<Method,PropertyMeta>();
  AlreadyLoadedTransformer transformer=new AlreadyLoadedTransformer(getterMetas);
  PropertyMeta pm1=PropertyMetaTestBuilder.completeBean(Void.class,String.class).field("name").accessors().type(PropertyType.SIMPLE).build();
  getterMetas.put(pm1.getGetter(),pm1);
  List<PropertyMeta> list=FluentIterable.from(Arrays.asList(pm1.getGetter())).transform(transformer).toList();
  assertThat(list).containsExactly(pm1);
}
