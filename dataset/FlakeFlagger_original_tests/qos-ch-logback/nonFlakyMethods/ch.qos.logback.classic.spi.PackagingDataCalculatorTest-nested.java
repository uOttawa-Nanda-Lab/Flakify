@Test public void nested() throws Exception {
  Throwable t=TeztHelper.makeNestedException(3);
  ThrowableProxy tp=new ThrowableProxy(t);
  PackagingDataCalculator pdc=tp.getPackagingDataCalculator();
  pdc.calculate(tp);
  verify(tp);
}
