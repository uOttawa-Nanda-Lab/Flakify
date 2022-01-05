@Test public void testEquals() throws Exception {
  LdapServerProperties properties1=configuration.getLdapServerProperties();
  LdapServerProperties properties2=configuration.getLdapServerProperties();
  assertTrue("Properties object is same",properties1 != properties2);
  assertTrue("Objects are not equal",properties1.equals(properties2));
  assertTrue("Hash codes are not equal",properties1.hashCode() == properties2.hashCode());
  properties2.setSecondaryUrl("5.6.7.8:389");
  assertFalse("Objects are equal",properties1.equals(properties2));
}
