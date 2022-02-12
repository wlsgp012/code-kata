(ns dojo.problems-in-4clojure.045-intro-to-iterate)
;; https://4clojure.oxal.org/#/problem/45

(= [1 4 7 10 13] (take 5 (iterate #(+ 3 %) 1)))
