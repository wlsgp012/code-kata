(ns dojo.problems-in-4clojure.018-filter)
;; https://4clojure.oxal.org/#/problem/18

(= [6 7] (filter #(> % 5) '(3 4 5 6 7)))