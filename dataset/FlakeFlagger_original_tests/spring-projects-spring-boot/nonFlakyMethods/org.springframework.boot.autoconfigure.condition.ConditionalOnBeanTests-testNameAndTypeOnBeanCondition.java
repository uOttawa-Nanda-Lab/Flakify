@Test public void testNameAndTypeOnBeanCondition(){
  this.context.register(FooConfiguration.class,OnBeanNameAndTypeConfiguration.class);
  this.context.refresh();
  assertFalse(this.context.containsBean("bar"));
}
