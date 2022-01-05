@Test(expected=AchillesException.class) public void should_exception_trying_to_encode_non_supported_type_for_typed_query() throws Exception {
  EntityMeta meta=new EntityMeta();
  meta.encodeBoundValuesForTypedQueries(new Object[]{new CompleteBean()});
}
