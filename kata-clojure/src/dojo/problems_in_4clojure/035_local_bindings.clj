(ns dojo.problems-in-4clojure.035-local-bindings)
;; https://4clojure.oxal.org/#/problem/35

(def answer 7)

(= answer (let [x 5] (+ 2 x)))
(= answer (let [x 3, y 10] (- y x)))
(= answer (let [x 21] (let [y 3] (/ x y))))
