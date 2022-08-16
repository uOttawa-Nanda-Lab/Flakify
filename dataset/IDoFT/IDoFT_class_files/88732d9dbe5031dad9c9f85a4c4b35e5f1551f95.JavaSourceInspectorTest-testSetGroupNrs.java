package de.strullerbaumann.visualee.examiner;

/*
 * #%L
 * visualee
 * %%
 * Copyright (C) 2013 Thomas Struller-Baumann
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
import de.strullerbaumann.visualee.dependency.boundary.DependencyContainer;
import de.strullerbaumann.visualee.source.boundary.JavaSourceContainer;
import de.strullerbaumann.visualee.source.entity.JavaSource;
import de.strullerbaumann.visualee.source.entity.JavaSourceFactory;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Thomas Struller-Baumann (contact at struller-baumann.de)
 */
public class JavaSourceInspectorTest {

   public JavaSourceInspectorTest() {
   }

   @Test
   public void testFindAndSetAttributesIgnoreComments() {
      JavaSource javaSource;
      String sourceCode;

      javaSource = JavaSourceFactory.getInstance().newJavaSource("Cocktail");
      sourceCode = "//@Entity\n"
              + "//@Access(AccessType.FIELD)\n"
              + "public class Cocktail implements Comparable<Cocktail>\n"
              + "{\n"
              + "// @Id\n"
              + "private String             id;\n"
              + "private String             name;\n"
              + "// @ManyToMany\n"
              + "private Set<CocktailZutat> zutaten = new HashSet<CocktailZutat>();\n"
              + "// @ManyToOne\n"
              + "private CocktailZutat      basisZutat;\n"
              + "public Cocktail(String id, String name)\n"
              + "{\n"
              + "this.id = id;\n"
              + "this.name = name;\n"
              + "}\n";

      javaSource.setSourceCode(sourceCode);
      JavaSourceContainer.getInstance().add(javaSource);
      JavaSourceInspector.getInstance().examine();
      assertEquals(0, DependencyContainer.getInstance().getDependencies(javaSource).size());
   }

   @Test
   public void testFindAndSetAttributesIgnoreCommentBlocks() {
      JavaSource javaSource;
      String sourceCode;

      javaSource = JavaSourceFactory.getInstance().newJavaSource("CocktailModel");
      sourceCode = "@Model\n"
              + "public class CocktailModel implements Serializable\n"
              + "{\n"
              + "  private List<Cocktail>     nonAlcoholicCocktails;\n"
              + "  private List<Cocktail>     alcoholicCocktails;\n"
              + "  /*\n"
              + "  @Inject\n"
              + "  private CocktailRepository cocktailRepository;\n"
              + "    */\n"
              + "  public List<Cocktail> getNonAlcoholicCocktails()\n"
              + "{\n"
              + "return this.nonAlcoholicCocktails;\n"
              + "}\n";

      javaSource.setSourceCode(sourceCode);
      JavaSourceContainer.getInstance().add(javaSource);
      JavaSourceInspector.getInstance().examine();
      assertEquals(0, DependencyContainer.getInstance().getDependencies(javaSource).size());
   }

   @Test
   public void testSetGroupNrs() {
      JavaSourceContainer.getInstance().clear();
      JavaSource javaSource1 = JavaSourceFactory.getInstance().newJavaSource("JavaSource1");
      javaSource1.setName("TestClass1");
      javaSource1.setPackagePath("package de.strullerbaumann.visualee.test.1;");
      JavaSourceContainer.getInstance().add(javaSource1);

      JavaSource javaSource2 = JavaSourceFactory.getInstance().newJavaSource("JavaSource2");
      javaSource2.setName("TestClass2");
      javaSource2.setPackagePath("package de.strullerbaumann.visualee.test.2;");
      JavaSourceContainer.getInstance().add(javaSource2);

      JavaSource javaSource3 = JavaSourceFactory.getInstance().newJavaSource("JavaSource3");
      javaSource3.setName("TestClass3");
      javaSource3.setPackagePath("package de.strullerbaumann.visualee.test.1;");
      JavaSourceContainer.getInstance().add(javaSource3);

      JavaSourceInspector.getInstance().setGroupNrs();

      assertEquals(1, javaSource1.getGroup());
      assertEquals(javaSource1.getGroup(), javaSource3.getGroup());
      assertEquals(2, javaSource2.getGroup());
   }
}
