@Test public void disableIfNotConfiguredOtherwiseWithConfig(){
  load(DisabledIfNotConfiguredOtherwiseConfig.class,"simple.myProperty:true");
  assertTrue(this.context.containsBean("foo"));
}
