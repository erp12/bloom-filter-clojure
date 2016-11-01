[![Clojars Project](https://img.shields.io/clojars/v/org.clojars.erp12/bloom-filter.svg)](https://clojars.org/org.clojars.erp12/bloom-filter)

# bloom-filter

A simple implementation of bloom filters in Clojure


## Installation

Add the following to your project dependencies:

```
[org.clojars.erp12/bloom-filter "0.4.0"]
```

Then call `lein deps` from the project folder.


## Usage

```clj
(require '[bloom-filter.core :as bf])

;; Creating a bloom filter requires 2 peiced of information.
;; 1. The number of items that you expect to store. Lets use 10000.
;; 2. The accpetable false positive rate. Lets use 1 out of 100.
(def my-bloom-filter (atom (bf/make-bloom-filter 10000 0.01)))

;; To preprocess the bloom filter we call add-elements-bloom-filter
(swap! my-bloom-filter #(bf/add-elements-bloom-filter % (range 10000)))

;; To check if an item has been seen before we call check-element-bloom-filter
(bf/check-element-bloom-filter @my-bloom-filter 555)   ;; definately `true`
(bf/check-element-bloom-filter @my-bloom-filter -11)   ;; probably `false`
(bf/check-element-bloom-filter @my-bloom-filter 12345) ;; probably `false`

```

## Examples

In the `/test/bloom_filter/core_test.clj` file, there are 2 example usages. The first is nearly identical to the above example, but there also is a second example about determining if an email address is on a whitelist.


## Contribute

Everyone is encouraged to submit pull requests, or open issues with feature requests.

## License

Copyright © 2016 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
