@Test public void defaultValueIsSetDifferentValue(){
  load(DefaultValueConfig.class,"simple.myProperty:another");
  assertFalse(this.context.containsBean("foo"));
}
