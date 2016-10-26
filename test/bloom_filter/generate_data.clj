
(ns bloom-filter.generate-data
  (:gen-class))


;; Utility Functions

(defn random-string
  [length]
  (apply str (repeatedly length #(rand-nth "abcdefghijklmnopqrstuvwxyz"))))

(defn random-domain
  []
  (rand-nth [".com" ".net" ".org" ".edu"]))

(defn random-email-address
  []
  (str (random-string 12) "@" (random-string 1) "mail" (random-domain)))


;; Generate email address dataset

(defn make-email-address-datset
  [num-emails]
  (let [whitelist (repeatedly num-emails
                              random-email-address)
        test-set (concat (repeatedly (/ num-emails 2)
                                     random-email-address)
                         (repeatedly (/ num-emails 2)
                                     #(rand-nth whitelist)))]
    {:whitelist whitelist
     :test-set test-set}))
