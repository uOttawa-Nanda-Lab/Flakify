@Test public void testGetSubProperties(){
  MutablePropertySources propertySources=new MutablePropertySources();
  Map<String,Object> source=new HashMap<String,Object>();
  MapPropertySource propertySource=new MapPropertySource("test",source);
  propertySources.addFirst(propertySource);
  String KEY_PREFIX="user";
  String KEY_NAME="name";
  String KEY_AGE="age";
  Map<String,String> result=PropertySourcesUtils.getSubProperties(propertySources,KEY_PREFIX);
  Assert.assertEquals(Collections.emptyMap(),result);
  source.put(KEY_PREFIX + "." + KEY_NAME,"Mercy");
  source.put(KEY_PREFIX + "." + KEY_AGE,31);
  Map<String,Object> expected=new HashMap<String,Object>();
  expected.put(KEY_NAME,"Mercy");
  expected.put(KEY_AGE,"31");
  result=PropertySourcesUtils.getSubProperties(propertySources,KEY_PREFIX);
  Assert.assertEquals(expected,result);
  result=PropertySourcesUtils.getSubProperties(propertySources,"");
  Assert.assertEquals(Collections.emptyMap(),result);
  result=PropertySourcesUtils.getSubProperties(propertySources,"no-exists");
  Assert.assertEquals(Collections.emptyMap(),result);
  source.put(KEY_PREFIX + ".app.name","${info.name}");
  source.put("info.name","Hello app");
  result=PropertySourcesUtils.getSubProperties(propertySources,KEY_PREFIX);
  String appName=result.get("app.name");
  Assert.assertEquals("Hello app",appName);
}
