
[comment]: # (Start Badges)

[![Build Status](https://travis-ci.org/47deg/case-classy.svg?branch=master)](https://travis-ci.org/47deg/case-classy) [![codecov.io](http://codecov.io/github/47deg/case-classy/coverage.svg?branch=master)](http://codecov.io/github/47deg/case-classy?branch=master) [![Maven Central](https://img.shields.io/badge/maven%20central-0.4.0-green.svg)](https://oss.sonatype.org/#nexus-search;gav~com.47deg~classy*) [![License](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://raw.githubusercontent.com/47deg/case-classy/master/LICENSE) [![Latest version](https://img.shields.io/badge/case--classy-0.4.0-green.svg)](https://index.scala-lang.org/47deg/case-classy) [![Scala.js](http://scala-js.org/assets/badges/scalajs-0.6.15.svg)](http://scala-js.org) [![GitHub Issues](https://img.shields.io/github/issues/47deg/case-classy.svg)](https://github.com/47deg/case-classy/issues)

[comment]: # (End Badges)

## Case Classy

### Introduction

Case classy is a tiny library to make it easy to decode untyped
structured data into case class hierarchies of your choosing. It's
completely modular, support Scala 2.11 and
2.12, [ScalaJS](https://www.scala-js.org) ready, and the core module
has _zero_ external dependencies.

[comment]: # (Start Replace)

```scala
// required
libraryDependencies += "com.47deg" %% "classy-core"            % "0.4.0"

// at least one required
libraryDependencies += "com.47deg" %% "classy-config-typesafe" % "0.4.0"
libraryDependencies += "com.47deg" %% "classy-config-shocon"   % "0.4.0"

// optional
libraryDependencies += "com.47deg" %% "classy-generic"         % "0.4.0"
libraryDependencies += "com.47deg" %% "classy-cats"            % "0.4.0"
```

[comment]: # (End Replace)

The modules provide the following support:

 * `classy-core`: Basic set of configuration decoders and combinators. *required*
 * `classy-generic`: Automatic derivation for your case class
   hierarchies. *depends on [shapeless](https://github.com/milessabin/shapeless)*
 * `classy-config-typesafe`: Support for [Typesafe's Config](https://github.com/typesafehub/config) library.
 * `classy-config-shocon`: Support for the [Shocon](https://github.com/unicredit/shocon) config library.
 * `classy-cats`: Instances for [Cats](https://github.com/typelevel/cats).

All module support ScalaJS except `classy-config-typesafe`.

### Documentation

Documentation is available on the [website](https://47deg.github.io/case-classy/).

### Quick Example

```scala
import classy.generic._
import classy.config._

// Our configuration class hierarchy
sealed trait Shape
case class Circle(radius: Double) extends Shape
case class Rectangle(length: Double, width: Double) extends Shape

case class MyConfig(
  someString: Option[String],
  shapes: List[Shape])

import com.typesafe.config.Config
val decoder1 = deriveDecoder[Config, MyConfig]
```

```scala
decoder1.fromString("""shapes = []""")
// res4: Either[classy.DecodeError,MyConfig] = Right(MyConfig(None,List()))

decoder1.fromString("""
  someString = "hello"
  shapes     = []""")
// res5: Either[classy.DecodeError,MyConfig] = Right(MyConfig(Some(hello),List()))

decoder1.fromString("""shapes = [
  { circle    { radius: 200.0 } },
  { rectangle { length: 10.0, width: 20.0 } }
]""")
// res6: Either[classy.DecodeError,MyConfig] = Right(MyConfig(None,List(Circle(200.0), Rectangle(10.0,20.0))))

// mismatched config
val res = decoder1.fromString("""shapes = [
  { rectangle { radius: 200.0 } },
  { circle    { length: 10.0, width: 20.0 } }
]""")
// res: Either[classy.DecodeError,MyConfig] = Left(AtPath(shapes,And(AtIndex(0,Or(AtPath(circle,Missing),List(AtPath(rectangle,And(AtPath(length,Missing),List(AtPath(width,Missing))))))),List(AtIndex(1,Or(AtPath(circle,AtPath(radius,Missing)),List(AtPath(rectangle,Missing))))))))

// error pretty printing
res.fold(
  error => error.toPrettyString,
  conf  => s"success: $conf")
// res9: String =
// errors.shapes (conjunction/AND):
//   [0] (disjunction/OR):
//     circle: missing value
//     rectangle (conjunction/AND):
//       length: missing value
//       width: missing value
//   [1] (disjunction/OR):
//     circle.radius: missing value
//     rectangle: missing value
```

## Case Classy in the wild

If you wish to add your library here please consider a PR to include it in the list below.

## Commercial Support

47 Degrees offers commercial support for the Case Classy library and associated technologies. To find out more, visit [47 Degrees' Open Source Support](https://www.47deg.com/services/open-source-support/).

[comment]: # (Start Copyright)
# Copyright

Case Classy is designed and developed by 47 Degrees

Copyright (C) 2017 47 Degrees. <http://47deg.com>

[comment]: # (End Copyright)
