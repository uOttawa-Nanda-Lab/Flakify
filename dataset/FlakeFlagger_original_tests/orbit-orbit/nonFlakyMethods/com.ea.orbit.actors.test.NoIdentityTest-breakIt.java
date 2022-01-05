@Test(expected=IllegalArgumentException.class) public void breakIt(){
  SingularThingFactory.getReference("aaa");
}
