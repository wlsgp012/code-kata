(ns dojo.problems-in-4clojure.006-vectors)
;; https://4clojure.oxal.org/#/problem/6

(= [:a :b :c] (list :a :b :c) (vec '(:a :b :c)) (vector :a :b :c))