package org.wfanet.anysketch.distributions;

import com.google.common.base.Preconditions;
import java.util.Map;

/**
 * Base for representing distributions -- a way of deterministically mapping an item and associated
 * metadata to a number.
 */
public abstract class Distribution {

  /**
   * Constructor.
   *
   * @param minValue the smallest value that {@link Distribution::apply} can return
   * @param maxValue the largest value that {@link Distribution::apply} can return
   */
  public Distribution(long minValue, long maxValue) {
    Preconditions.checkArgument(minValue <= maxValue);
    this.minValue = minValue;
    this.maxValue = maxValue;
  }

  private final long minValue;
  private final long maxValue;

  /** Returns the smallest value in this distribution. */
  public long getMinValue() {
    return minValue;
  }

  /** Returns the largest value in this distribution. */
  public long getMaxValue() {
    return maxValue;
  }

  /** Returns the cardinality of the range of values the distribution could return. */
  public long getSize() {
    return maxValue - minValue + 1;
  }

  /**
   * Calculates the value of the distribution for an item and associated metadata.
   *
   * @param item the element to map into the distribution
   * @param itemMetadata metadata for the element
   * @return a value between getMinValue() and getMaxValue(), inclusive
   */
  public abstract long apply(String item, Map<String, Long> itemMetadata);
}
