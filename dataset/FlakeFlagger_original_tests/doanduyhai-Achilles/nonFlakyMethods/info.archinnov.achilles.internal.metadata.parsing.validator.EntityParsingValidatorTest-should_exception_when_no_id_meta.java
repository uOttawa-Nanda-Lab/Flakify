@Test public void should_exception_when_no_id_meta() throws Exception {
  exception.expect(AchillesBeanMappingException.class);
  exception.expectMessage("The entity '" + CompleteBean.class.getCanonicalName() + "' should have at least one field with javax.persistence.Id/javax.persistence.EmbeddedId annotation");
  validator.validateHasIdMeta(CompleteBean.class,null);
}
