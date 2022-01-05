@Test public void useNonExistenceOfSystemPropertyToDefineAContextProperty() throws JoranException {
  assertNull(System.getProperty(sysKey));
  assertNull(context.getProperty(dynaKey));
  tc.doConfigure(CONDITIONAL_DIR_PREFIX + "ifSystem.xml");
  System.out.println(dynaKey + "=" + context.getProperty(dynaKey));
  assertNotNull(context.getProperty(dynaKey));
}
