@Test public void enabledIfNotConfiguredOtherwiseWithConfig(){
  load(EnabledIfNotConfiguredOtherwiseConfig.class,"simple.myProperty:false");
  assertFalse(this.context.containsBean("foo"));
}
