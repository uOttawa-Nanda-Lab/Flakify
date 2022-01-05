@Test public void should_throw_error_if_description_is_null(){
  thrown.expectNullPointerException(descriptionIsNull());
  new Condition<Object>((Description)null){
    @Override public boolean matches(    Object value){
      return false;
    }
  }
;
}
