(ns dojo.problems-in-4clojure.051-advanced-destructuing)
;; https://4clojure.oxal.org/#/problem/51

(= [1 2 [3 4 5] [1 2 3 4 5]] (let [[a b & c :as d] [1 2 3 4 5]] [a b c d]))
