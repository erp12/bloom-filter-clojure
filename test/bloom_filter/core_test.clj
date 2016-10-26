

(ns bloom-filter.core-test
  (:require [clojure.test :refer :all]
            [bloom-filter.core :refer :all]
            [bloom-filter.generate-data :as data]))


(def email-bloom-filter
  (let [dataset (data/make-email-address-datset 10000)
        bfilter (-> (make-bloom-filter 10000 0.01)
                    (add-elements-bloom-filter (:whitelist dataset)))
        results (map #(check-element-bloom-filter bfilter %) (:test-set dataset))
        num-allowed (count (filter true? results))
        num-blocked (count (filter false? results))]
    {:bloom-filter bfilter
     :results results
     :num-allowed num-allowed
     :num-blocked num-blocked}))

(deftest email-test-a
  (testing "Email Test A"
    (is (> (:num-allowed email-bloom-filter)
           (:num-blocked email-bloom-filter)))))

(deftest email-test-b
  (testing "Email Test B"
    (is (< (- (:num-allowed email-bloom-filter)
              (:num-blocked email-bloom-filter))
           200))))



