@Test(expected=RuntimeException.class) public void testExernalConfigLoadingBreaksWhenFileDoesNotExist(){
  System.setProperty(NinjaProperties.NINJA_EXTERNAL_CONF,"conf/non_existing.conf");
  NinjaProperties ninjaProperties=new NinjaPropertiesImpl(NinjaMode.dev);
}
