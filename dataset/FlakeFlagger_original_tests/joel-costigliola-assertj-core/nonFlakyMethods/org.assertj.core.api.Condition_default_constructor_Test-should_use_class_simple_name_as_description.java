@Test public void should_use_class_simple_name_as_description(){
  Condition<Object> condition=new Condition<Object>(){
    @Override public boolean matches(    Object value){
      return false;
    }
  }
;
  assertEquals(condition.getClass().getSimpleName(),condition.description.value());
}
