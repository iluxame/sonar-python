/*
 * SonarQube Python Plugin
 * Copyright (C) 2011 SonarSource and Waleri Enns
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.python.checks;

import org.junit.Test;
import org.sonar.python.PythonAstScanner;
import org.sonar.squidbridge.api.SourceFile;
import org.sonar.squidbridge.checks.CheckMessagesVerifier;

import java.io.File;

public class ReturnYieldOutsideFunctionCheckTest {

  @Test
  public void test() {
    ReturnYieldOutsideFunctionCheck check = new ReturnYieldOutsideFunctionCheck();

    SourceFile file = PythonAstScanner.scanSingleFile(new File("src/test/resources/checks/returnYieldOutsideFunction.py"), check);
    CheckMessagesVerifier.verify(file.getCheckMessages())
        .next().atLine(3).withMessage(String.format(ReturnYieldOutsideFunctionCheck.MESSAGE, "return"))
        .next().atLine(12).withMessage(String.format(ReturnYieldOutsideFunctionCheck.MESSAGE, "return"))
        .next().atLine(16).withMessage(String.format(ReturnYieldOutsideFunctionCheck.MESSAGE, "yield"))
        .noMore();
  }

}
