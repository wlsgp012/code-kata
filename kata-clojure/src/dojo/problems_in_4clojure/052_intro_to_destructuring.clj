(ns dojo.problems-in-4clojure.052-intro-to-destructuring)
;; https://4clojure.oxal.org/#/problem/52

(= [2 4] (let [[a b c d e f g] (range)] [c e]))
