@Test public void should_exception_when_incorrect_getter() throws Exception {
class Test {
    @SuppressWarnings("unused") String name;
    @SuppressWarnings("unused") public Long getName(){
      return 1L;
    }
  }
  expectedEx.expect(AchillesBeanMappingException.class);
  expectedEx.expectMessage("The getter for field 'name' of type 'null' does not return correct type");
  introspector.findGetter(Test.class,Test.class.getDeclaredField("name"));
}
