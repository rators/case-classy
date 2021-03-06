/*
 * Copyright 2017 47 Degrees, LLC. <http://www.47deg.com>
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

package classy

import scala.concurrent.duration.Duration
import scala.concurrent.duration.FiniteDuration
import java.util.UUID

import Read.Reinterpret
import Read.Reinterpret.{ instance => reinterpret }

private[classy] trait DefaultReinterpretStringInstances {

  implicit val reinterpretStringToBoolean: Reinterpret[String, Boolean] =
    reinterpret(decoders.stringToBoolean)

  implicit val reinterpretStringToByte: Reinterpret[String, Byte] =
    reinterpret(decoders.stringToByte)

  implicit val reinterpretStringToShort: Reinterpret[String, Short] =
    reinterpret(decoders.stringToShort)

  implicit val reinterpretStringToInt: Reinterpret[String, Int] =
    reinterpret(decoders.stringToInt)

  implicit val reinterpretStringToLong: Reinterpret[String, Long] =
    reinterpret(decoders.stringToLong)

  implicit val reinterpretStringToFloat: Reinterpret[String, Float] =
    reinterpret(decoders.stringToFloat)

  implicit val reinterpretStringToDouble: Reinterpret[String, Double] =
    reinterpret(decoders.stringToDouble)

  implicit val reinterpretStringToUUID: Reinterpret[String, UUID] =
    reinterpret(decoders.stringToUUID)

  implicit val reinterpretStringToDuration: Reinterpret[String, Duration] =
    reinterpret(decoders.stringToDuration)

  implicit val reinterpretStringToFiniteDuration: Reinterpret[String, FiniteDuration] =
    reinterpret(decoders.stringToFiniteDuration)

}
