/*
 * Copyright 2017 Michael Stringer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package software.purpledragon.xml.scalatest

import org.scalatest.matchers.{MatchResult, Matcher}
import software.purpledragon.xml.compare.XmlCompare

import scala.xml.Node

trait XmlMatchers {
  def beXml(expected: Node): Matcher[Node] = new XmlMatcher(expected)

//  def beExactXml(node: Node): Matcher[Node] = ???

  class XmlMatcher(expected: Node) extends Matcher[Node] {
    override def apply(actual: Node): MatchResult = {
      val diff = XmlCompare.compare(expected, actual)

      MatchResult(
        diff.isEqual,
        s"XML did not match - ${diff.message}",
        "XML matched"
      )
    }
  }
}
