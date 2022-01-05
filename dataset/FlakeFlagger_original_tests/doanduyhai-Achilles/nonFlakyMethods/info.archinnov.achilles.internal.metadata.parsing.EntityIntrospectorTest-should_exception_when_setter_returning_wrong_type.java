@Test public void should_exception_when_setter_returning_wrong_type() throws Exception {
@SuppressWarnings("unused") class Test {
    String name;
    public String getName(){
      return name;
    }
    public Long setName(    String name){
      return 1L;
    }
  }
  expectedEx.expect(AchillesBeanMappingException.class);
  expectedEx.expectMessage("The setter for field 'name' of type 'null' does not return correct type or does not have the correct parameter");
  introspector.findSetter(Test.class,Test.class.getDeclaredField("name"));
}
