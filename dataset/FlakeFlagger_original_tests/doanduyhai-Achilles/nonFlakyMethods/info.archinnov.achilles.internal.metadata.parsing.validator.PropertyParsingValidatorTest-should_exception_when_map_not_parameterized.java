@Test public void should_exception_when_map_not_parameterized() throws Exception {
class Test {
    @SuppressWarnings({"rawtypes","unused"}) public Map map;
  }
  Field mapField=Test.class.getField("map");
  exception.expect(AchillesBeanMappingException.class);
  exception.expectMessage("The Map type should be parameterized for the entity '" + Test.class.getCanonicalName() + "'");
  validator.validateMapGenerics(mapField,Test.class);
}
