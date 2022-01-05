@Test public void should_find_getter_from_boolean_as_isOld() throws Exception {
@SuppressWarnings("unused") class Test {
    boolean old;
    public boolean isOld(){
      return old;
    }
    public void setOld(    boolean old){
      this.old=old;
    }
  }
  Method[] accessors=introspector.findAccessors(Test.class,Test.class.getDeclaredField("old"));
  assertThat(accessors[0].getName()).isEqualTo("isOld");
}
