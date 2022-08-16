/*
 * Copyright 2016 Thomas Struller-Baumann.
 *
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
 */
package de.strullerbaumann.visualee.filter.entity;

/*
 * #%L
 * visualee
 * %%
 * Copyright (C) 2013 - 2016 Thomas Struller-Baumann
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

import de.strullerbaumann.visualee.filter.boundary.FilterContainer;
import de.strullerbaumann.visualee.source.entity.JavaSource;
import de.strullerbaumann.visualee.source.entity.JavaSourceFactory;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Thomas Struller-Baumann (contact at struller-baumann.de)
 */
public class SourcecodeFilterTest {

   private static JavaSource javaSource;

   public SourcecodeFilterTest() {
   }

   @BeforeClass
   public static void setUpClass() {
      FilterContainer.getInstance().clear();

      String sourceCode;
      javaSource = JavaSourceFactory.getInstance().newJavaSource("DatabaseProducer");
      sourceCode = "package org.agoncal.application.petstore.util;\n"
              + "import javax.enterprise.inject.Produces;\n"
              + "import javax.persistence.EntityManager;\n"
              + "import javax.persistence.PersistenceContext;\n"
              + "public class DatabaseProducer {\n"
              + "@Produces\n"
              + "    @PersistenceContext(unitName = \"applicationPetstorePU\")\n"
              + "    private EntityManager em;\n"
              + "}\n";

      javaSource.setSourceCode(sourceCode);
   }

   @AfterClass
   public static void tearDownClass() {
   }

   @Before
   public void setUp() {
   }

   @After
   public void tearDown() {
   }

   @Test
   public void testIsOkExcludeTrue() {
      SourcecodeFilter filter = new SourcecodeFilter();
      filter.setFilterToken("EntityManager");
      filter.setExclude(true);
      FilterContainer.getInstance().add(filter);

      assertEquals(false, FilterContainer.getInstance().isOk(javaSource));
   }

   @Test
   public void testIsOkExcludeFalse() {
      SourcecodeFilter filter = new SourcecodeFilter();
      filter.setFilterToken("XYZEntityManager");
      filter.setExclude(true);
      FilterContainer.getInstance().add(filter);

      assertEquals(true, FilterContainer.getInstance().isOk(javaSource));
   }

   @Test
   public void testIsOkIncludeTrue() {
      SourcecodeFilter filter = new SourcecodeFilter();
      filter.setFilterToken("EntityManager");
      filter.setExclude(false);
      FilterContainer.getInstance().add(filter);

      assertEquals(true, FilterContainer.getInstance().isOk(javaSource));
   }

   @Test
   public void testIsOkIncludeFalse() {
      SourcecodeFilter filter = new SourcecodeFilter();
      filter.setFilterToken("XYZEntityManager");
      filter.setExclude(false);
      FilterContainer.getInstance().add(filter);

      assertEquals(false, FilterContainer.getInstance().isOk(javaSource));
   }

   @Test
   public void testToString() {
      SourcecodeFilter filter = new SourcecodeFilter();
      assertNotNull(filter.toString());
   }

   @Test
   public void testGetType() {
      assertNotNull(SourcecodeFilter.getType());
   }

}
