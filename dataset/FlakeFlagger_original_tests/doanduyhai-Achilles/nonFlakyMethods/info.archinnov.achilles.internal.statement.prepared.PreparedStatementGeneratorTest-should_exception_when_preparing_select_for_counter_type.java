@Test public void should_exception_when_preparing_select_for_counter_type() throws Exception {
  PropertyMeta nameMeta=completeBean(Void.class,Long.class).field("count").type(PropertyType.COUNTER).build();
  EntityMeta meta=new EntityMeta();
  meta.setClassName("entity");
  exception.expect(IllegalArgumentException.class);
  exception.expectMessage("Cannot prepare statement for property 'count' of entity 'entity' because it is a counter type");
  generator.prepareSelectField(session,meta,nameMeta);
}
