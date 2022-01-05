@Test public void should_parse_embedded_id() throws Exception {
@SuppressWarnings("unused") class Test {
    @EmbeddedId private EmbeddedKey id;
    public EmbeddedKey getId(){
      return id;
    }
    public void setId(    EmbeddedKey id){
      this.id=id;
    }
  }
  PropertyParsingContext context=newContext(Test.class,Test.class.getDeclaredField("id"));
  context.setEmbeddedId(true);
  PropertyMeta meta=parser.parse(context);
  Field userIdField=EmbeddedKey.class.getDeclaredField("userId");
  Method userIdGetter=EmbeddedKey.class.getDeclaredMethod("getUserId");
  Method userIdSetter=EmbeddedKey.class.getDeclaredMethod("setUserId",Long.class);
  Field nameField=EmbeddedKey.class.getDeclaredField("name");
  Method nameGetter=EmbeddedKey.class.getDeclaredMethod("getName");
  Method nameSetter=EmbeddedKey.class.getDeclaredMethod("setName",String.class);
  assertThat(meta.getPropertyName()).isEqualTo("id");
  assertThat(meta.<EmbeddedKey>getValueClass()).isEqualTo(EmbeddedKey.class);
  EmbeddedIdProperties embeddedIdProperties=meta.getEmbeddedIdProperties();
  assertThat(embeddedIdProperties).isNotNull();
  assertThat(embeddedIdProperties.getComponentClasses()).contains(Long.class,String.class);
  assertThat(embeddedIdProperties.getComponentNames()).contains("id","name");
  assertThat(embeddedIdProperties.getComponentFields()).contains(userIdField,nameField);
  assertThat(embeddedIdProperties.getComponentGetters()).contains(userIdGetter,nameGetter);
  assertThat(embeddedIdProperties.getComponentSetters()).contains(userIdSetter,nameSetter);
  assertThat(context.getPropertyMetas()).hasSize(1);
}
