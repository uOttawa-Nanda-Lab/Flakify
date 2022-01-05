@Test public void should_exception_when_infering_value_type_from_raw_list() throws Exception {
@SuppressWarnings({"rawtypes","unused"}) class Test {
    private List friends;
  }
  Type type=Test.class.getDeclaredField("friends").getGenericType();
  expectedEx.expect(AchillesBeanMappingException.class);
  expectedEx.expectMessage("The type '" + type.getClass().getCanonicalName() + "' of the entity 'null' should be parameterized");
  parser.inferValueClassForListOrSet(type,Test.class);
}
