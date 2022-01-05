@Test public void smoke() throws Exception {
  Throwable t=new Throwable("x");
  ThrowableProxy tp=new ThrowableProxy(t);
  PackagingDataCalculator pdc=tp.getPackagingDataCalculator();
  pdc.calculate(tp);
  verify(tp);
  tp.fullDump();
}
