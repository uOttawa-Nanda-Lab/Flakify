@Test public void should_exception_when_index_not_allowed_on_embedded_id() throws Exception {
class Test {
    @EmbeddedId @Index public String firstName;
  }
  Field nameField=Test.class.getField("firstName");
  Map<String,PropertyMeta> propertyMetas=new HashMap<String,PropertyMeta>();
  propertyMetas.put("firstName",null);
  when(context.getCurrentField()).thenReturn(nameField);
  when(context.getCurrentPropertyName()).thenReturn("firstName");
  when(context.getPropertyMetas()).thenReturn(propertyMetas);
  when(context.isPrimaryKey()).thenReturn(true);
  when(context.isEmbeddedId()).thenReturn(true);
  when(context.<Test>getCurrentEntityClass()).thenReturn(Test.class);
  exception.expect(AchillesBeanMappingException.class);
  exception.expectMessage("Property 'firstName' of entity 'null' is part of a compound primary key and therefore cannot be indexed");
  validator.validateIndexIfSet(context);
}
