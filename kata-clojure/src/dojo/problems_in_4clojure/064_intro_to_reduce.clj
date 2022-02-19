(ns dojo.problems-in-4clojure.064-intro-to-reduce)
;; https://4clojure.oxal.org/#/problem/64

(def sol +)

(= 15 (reduce sol [1 2 3 4 5]))
(=  0 (reduce sol []))
(=  6 (reduce sol 1 [2 3]))
