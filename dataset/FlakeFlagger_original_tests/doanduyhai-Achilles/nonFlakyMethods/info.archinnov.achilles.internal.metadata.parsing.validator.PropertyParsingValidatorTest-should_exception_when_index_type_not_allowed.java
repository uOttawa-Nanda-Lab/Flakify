@Test public void should_exception_when_index_type_not_allowed() throws Exception {
class Test {
    @Index public Map<?,?> firstName;
  }
  Field nameField=Test.class.getField("firstName");
  Map<String,PropertyMeta> propertyMetas=new HashMap<String,PropertyMeta>();
  propertyMetas.put("firstName",null);
  when(context.getCurrentField()).thenReturn(nameField);
  when(context.getCurrentPropertyName()).thenReturn("firstName");
  when(context.getPropertyMetas()).thenReturn(propertyMetas);
  when(context.<Test>getCurrentEntityClass()).thenReturn(Test.class);
  exception.expect(AchillesBeanMappingException.class);
  exception.expectMessage("Property 'firstName' of entity 'null' cannot be indexed because the type 'java.util.Map' is not supported");
  validator.validateIndexIfSet(context);
}
