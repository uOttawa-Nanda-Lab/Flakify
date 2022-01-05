@Test public void enabledIfNotConfiguredOtherwiseWithConfigDifferentCase(){
  load(EnabledIfNotConfiguredOtherwiseConfig.class,"simple.my-property:FALSE");
  assertFalse(this.context.containsBean("foo"));
}
