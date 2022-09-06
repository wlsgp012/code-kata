(ns dojo.problems-in-4clojure.162-logical-falsity-and-truth)

(def sol 1)

(= sol (if-not false 1 0))

(= sol (if-not nil 1 0))

(= sol (if true 1 0))

(= sol (if [] 1 0))

(= sol (if [0] 1 0))

(= sol (if 0 1 0))

(= sol (if 1 1 0))
