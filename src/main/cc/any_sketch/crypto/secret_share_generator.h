// Copyright 2023 The Cross-Media Measurement Authors
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

#ifndef SRC_MAIN_CC_ANY_SKETCH_CRYPTO_SECRET_SHARE_GENERATOR_H_
#define SRC_MAIN_CC_ANY_SKETCH_CRYPTO_SECRET_SHARE_GENERATOR_H_

#include <vector>

#include "absl/status/statusor.h"
#include "wfa/any_sketch/secret_share.pb.h"

using wfa::any_sketch::SecretShare;
using wfa::any_sketch::SecretShareParameter;

namespace wfa::any_sketch::crypto {

absl::StatusOr<SecretShare> GenerateSecretShares(
    const SecretShareParameter& secret_share_parameter,
    const absl::Span<const uint32_t> input);

}  // namespace wfa::any_sketch::crypto

#endif  // SRC_MAIN_CC_ANY_SKETCH_CRYPTO_SECRET_SHARE_GENERATOR_H_
