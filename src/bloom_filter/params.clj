(ns bloom-filter.params
  (:gen-class)
  (:require [clojure.math.numeric-tower :as math]))

;; Functions to help find the best parameters for the bloom filter.

(defn optimal-number-of-bits
  [num-items acceptable-false-positive-rate]
  (/ (* (- num-items)
        (Math/log acceptable-false-positive-rate))
     (math/expt (Math/log 2)
               2)))

(defn optimal-number-hash-functions
  [num-items num-bits]
  (* (/ num-bits num-items)
     (Math/log 2)))

(defn optimal-bloom-filter-params
  [num-items acceptable-false-positive-rate]
  (let [num-bits (optimal-number-of-bits num-items 0.01)
        num-hash-funcs (optimal-number-hash-functions num-items num-bits)]
    {:num-items num-items
     :aprox-false-pos-rate acceptable-false-positive-rate
     :num-bits (Math/round num-bits)
     :num-hash-funcs (Math/round num-hash-funcs)}))



;; Suppose we plan on storing 216,553 items
;; And we are fine with a false positive rate of 1%

;; (optimal-bloom-filter-params 216553 0.01)




