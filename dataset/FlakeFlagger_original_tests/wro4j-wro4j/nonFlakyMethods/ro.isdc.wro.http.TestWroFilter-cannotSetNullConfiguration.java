@Test(expected=NullPointerException.class) public void cannotSetNullConfiguration(){
  victim.setConfiguration(null);
}
