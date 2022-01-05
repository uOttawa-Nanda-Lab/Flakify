@Test(expected=NullPointerException.class) public void shouldNotAcceptNullUri2(){
  Resource.create(null);
}
