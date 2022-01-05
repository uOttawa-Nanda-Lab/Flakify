@Test public void nested(){
  Throwable t=TeztHelper.makeNestedException(1);
  verify(t);
}
