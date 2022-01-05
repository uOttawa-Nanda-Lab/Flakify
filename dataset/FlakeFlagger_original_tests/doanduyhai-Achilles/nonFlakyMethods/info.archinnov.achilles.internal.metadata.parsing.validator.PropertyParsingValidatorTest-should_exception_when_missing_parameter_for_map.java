@Test public void should_exception_when_missing_parameter_for_map() throws Exception {
class Test {
    @SuppressWarnings("unused") public List<String> map;
  }
  Field mapField=Test.class.getField("map");
  exception.expect(AchillesBeanMappingException.class);
  exception.expectMessage("The Map type should be parameterized with <K,V> for the entity '" + Test.class.getCanonicalName() + "'");
  validator.validateMapGenerics(mapField,Test.class);
}
