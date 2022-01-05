@Test public void should_exception_when_asking_primary_key_on_non_id_field() throws Exception {
  CompleteBean entity=new CompleteBean();
  PropertyMeta pm=new PropertyMeta();
  pm.setPropertyName("property");
  pm.setType(SIMPLE);
  exception.expect(IllegalStateException.class);
  exception.expectMessage("Cannot get primary key on a non id field 'property'");
  pm.getPrimaryKey(entity);
}
