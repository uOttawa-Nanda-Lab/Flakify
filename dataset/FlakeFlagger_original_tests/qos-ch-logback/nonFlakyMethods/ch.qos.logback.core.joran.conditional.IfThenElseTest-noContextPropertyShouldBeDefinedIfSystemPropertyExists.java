@Test public void noContextPropertyShouldBeDefinedIfSystemPropertyExists() throws JoranException {
  System.setProperty(sysKey,"a");
  assertNull(context.getProperty(dynaKey));
  System.out.println("before " + dynaKey + "="+ context.getProperty(dynaKey));
  tc.doConfigure(CONDITIONAL_DIR_PREFIX + "ifSystem.xml");
  System.out.println(dynaKey + "=" + context.getProperty(dynaKey));
  assertNull(context.getProperty(dynaKey));
}
