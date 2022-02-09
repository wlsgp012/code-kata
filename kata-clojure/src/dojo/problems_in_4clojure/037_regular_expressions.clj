(ns dojo.problems-in-4clojure.037-regular-expressions)
;; https://4clojure.oxal.org/#/problem/37

(= "ABC" (apply str (re-seq #"[A-Z]+" "bA1B3Ce ")))
