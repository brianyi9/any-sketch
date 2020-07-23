package org.wfanet.anysketch;

import com.google.common.base.Preconditions;

/**
 * ExponentialIndexFunction maps the output of a {@link HashFunction} to a coordinate using
 * exponential distribution with param rate and multiplies the output with param size.
 */
class ExponentialIndexFunction implements IndexFunction {

  private double rate;
  private long size;

  /**
   * Creates ExponentialIndexFunction object expecting rate and size arguments.
   *
   * @param rate Rate of the exponential distribution
   * @param size Number of legionaries
   */
  public ExponentialIndexFunction(double rate, long size) {
    Preconditions.checkArgument(rate > 0);
    Preconditions.checkArgument(size > 0);
    this.rate = rate;
    this.size = size;
  }

  /**
   * Returns the index of the hashmap given the hashed key
   *
   * @param fingerprint hashed key
   * <p>ExponentialIndexFunction getIndex uses inverse CDF of the truncated exponential
   * distribution. To sample from number of legionaries, it multiplies the result with the size.
   *
   * It is possible to precompute inverse CDF for each register and do a O(log(size)) lookup for a
   * fingerprint. This implementation takes a simpler approach of calculating inverse CDF for each
   * fingerprint separately.
   */
  public long getIndex(long fingerprint) {
    Preconditions.checkArgument(fingerprint > 0);
    double u = (double) fingerprint / (double) Long.MAX_VALUE;
    double x = 1 - Math.log(Math.exp(this.rate) + u * (1 - Math.exp(this.rate))) / this.rate;
    return (long) Math.floor(x * this.size);
  }

  /**
   * Returns the max index value as output value to calculate next indexes
   */
  public long maxIndex() {
    return this.size - 1;
  }

  /**
   * Returns the max supported hash value to divide fingerprint into chunks
   */
  public long maxSupportedHash() {
    return Long.MAX_VALUE;
  }
}