@Test public void should_exception_when_duplicate_property_meta() throws Exception {
  Map<String,PropertyMeta> propertyMetas=new HashMap<String,PropertyMeta>();
  propertyMetas.put("name",null);
  when(context.getCurrentPropertyName()).thenReturn("name");
  when(context.getPropertyMetas()).thenReturn(propertyMetas);
  when(context.<CompleteBean>getCurrentEntityClass()).thenReturn(CompleteBean.class);
  exception.expect(AchillesBeanMappingException.class);
  exception.expectMessage("The property 'name' is already used for the entity '" + CompleteBean.class.getCanonicalName() + "'");
  validator.validateNoDuplicate(context);
}
