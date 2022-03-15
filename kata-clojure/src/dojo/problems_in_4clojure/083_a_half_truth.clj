(ns dojo.problems-in-4clojure.083-a-half-truth)
;; https://4clojure.oxal.org/#/problem/83

(defn sol
  [& bs]
  (and (contains? (set bs) true) (not-every? true? bs)))

(= false (sol false false))
(= true (sol true false))
(= false (sol true))
(= true (sol false true false))
(= false (sol true true true))
(= true (sol true true true false))

;; others
#(not (apply = %&))

(fn [& b] (and (not-every? true? b) (not (not-any? true? b))))
