(ns dojo.problems-in-4clojure.047-contain-yourself)
;; https://4clojure.oxal.org/#/problem/47

(def sol 4)

(contains? #{4 5 6} sol)
(contains? [1 1 1 1 1] sol)
(contains? {4 :a 2 :b} sol)
(not (contains? [1 2 4] sol))
