@Test public void should_exception_when_no_getter() throws Exception {
class Test {
    @SuppressWarnings("unused") String name;
  }
  expectedEx.expect(AchillesBeanMappingException.class);
  expectedEx.expectMessage("The getter for field 'name' of type 'null' does not exist");
  introspector.findGetter(Test.class,Test.class.getDeclaredField("name"));
}
