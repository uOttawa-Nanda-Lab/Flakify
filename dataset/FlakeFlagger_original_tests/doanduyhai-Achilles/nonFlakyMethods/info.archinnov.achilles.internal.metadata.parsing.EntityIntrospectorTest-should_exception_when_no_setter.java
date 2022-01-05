@Test public void should_exception_when_no_setter() throws Exception {
class Test {
    String name;
    @SuppressWarnings("unused") public String getA(){
      return name;
    }
  }
  expectedEx.expect(AchillesBeanMappingException.class);
  expectedEx.expectMessage("The setter for field 'name' of type 'null' does not exist");
  introspector.findSetter(Test.class,Test.class.getDeclaredField("name"));
}
