/*
 * Copyright (C) 2015-2020 Lightbend Inc. <https://www.lightbend.com>
 */

package akka.util

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class VersionSpec extends AnyWordSpec with Matchers {

  "Version" should {

    "compare full version" in {
      new Version("1.2.3") should ===(new Version("1.2.3"))
      new Version("1.2.3") should !==(new Version("1.2.4"))
      new Version("1.2.4") should be > new Version("1.2.3")
      new Version("3.2.1") should be > new Version("1.2.3")
      new Version("3.2.1") should be < new Version("3.3.1")
    }

    "compare partial version" in {
      new Version("1.2") should ===(new Version("1.2"))
      new Version("1.2") should !==(new Version("1.3"))
      new Version("1.2.1") should be > new Version("1.2")
      new Version("2.4") should be > new Version("2.3")
      new Version("3.2") should be < new Version("3.2.7")
    }

    "compare extra" in {
      new Version("1.2.3-foo") should ===(new Version("1.2.3-foo"))
      new Version("1.2.3-foo") should !==(new Version("1.2.3-bar"))
      new Version("1.2-foo") should be > new Version("1.2.3")
      new Version("1.2.0-M1") should be < new Version("1.2.0")
      new Version("1.2.3-foo") should be > new Version("1.2.3-bar")
    }

    "compare dynver format" in {
      // dynver format
      new Version("1.0.10+3-1234abcd") should be < new Version("1.0.11")
      new Version("1.0.10+3-1234abcd") should be < new Version("1.0.10+10-1234abcd")

    }

    "compare extra without digits" in {
      new Version("foo") should ===(new Version("foo"))
      new Version("foo") should !==(new Version("bar"))
      new Version("foo") should be > new Version("1.2.3")
      new Version("foo") should be > new Version("bar")
    }
  }
}
