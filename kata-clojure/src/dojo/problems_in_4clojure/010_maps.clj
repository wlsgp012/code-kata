(ns dojo.problems-in-4clojure.010-maps)
;; https://4clojure.oxal.org/#/problem/10

(def answer 20)

(= answer ((hash-map :a 10, :b 20, :c 30) :b))
(= answer (:b {:a 10, :b 20, :c 30}))