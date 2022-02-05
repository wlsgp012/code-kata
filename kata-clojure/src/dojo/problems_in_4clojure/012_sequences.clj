(ns dojo.problems-in-4clojure.012-sequences)
;; https://4clojure.oxal.org/#/problem/12

(def answer 3)

(= answer (first '(3 2 1)))
(= answer (second [2 3 4]))
(= answer (last (list 1 2 3)))