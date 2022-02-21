(ns dojo.problems-in-4clojure.068-recurring-theme)
;; https://4clojure.oxal.org/#/problem/68


(= [7 6 5 4 3]
   (loop [x 5
          result []]
     (if (> x 0)
       (recur (dec x) (conj result (+ 2 x)))
       result)))
