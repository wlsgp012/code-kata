(ns dojo.problems-in-4clojure.015-double-down)
;; https://4clojure.oxal.org/#/problem/15

(def answer #(* 2 %))

(= (answer 2) 4)
(= (answer 3) 6)
(= (answer 11) 22)
(= (answer 7) 14)