@Test public void should_exception_when_setter_taking_wrong_type() throws Exception {
@SuppressWarnings("unused") class Test {
    String name;
    public String getName(){
      return name;
    }
    public void setName(    Long name){
    }
  }
  expectedEx.expect(AchillesBeanMappingException.class);
  expectedEx.expectMessage("The setter for field 'name' of type 'null' does not exist or is incorrect");
  introspector.findSetter(Test.class,Test.class.getDeclaredField("name"));
}
