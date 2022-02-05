(ns dojo.problems-in-4clojure.017-map)
;; https://4clojure.oxal.org/#/problem/17

(= (seq [6 7 8]) (map #(+ % 5) '(1 2 3)))