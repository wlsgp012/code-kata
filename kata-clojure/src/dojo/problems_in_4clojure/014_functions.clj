(ns dojo.problems-in-4clojure.014-functions)
;; https://4clojure.oxal.org/#/problem/14

(def answer 8)

(= answer ((fn add-five [x] (+ x 5)) 3))
(= answer ((fn [x] (+ x 5)) 3))
(= answer (#(+ % 5) 3))
(= answer ((partial + 5) 3))