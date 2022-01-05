@Test public void should_exception_when_getting_primary_key() throws Exception {
  PropertyMeta idMeta=PropertyMetaTestBuilder.completeBean(Void.class,String.class).type(ID).field("id").accessors().build();
  exception.expect(AchillesException.class);
  exception.expectMessage("Cannot get primary key from field 'id' of type '" + CompleteBean.class.getCanonicalName() + "' from entity 'bean'");
  invoker.getPrimaryKey("bean",idMeta);
}
