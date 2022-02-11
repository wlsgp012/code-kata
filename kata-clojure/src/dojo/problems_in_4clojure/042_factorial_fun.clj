(ns dojo.problems-in-4clojure.042-factorial-fun)
;; https://4clojure.oxal.org/#/problem/42

(defn answer
  [n]
  (apply * (take n (iterate inc 1))))

(= (answer 1) 1)
(= (answer 3) 6)
(= (answer 5) 120)
(= (answer 8) 40320)

;; others
#(apply * (range 1 (inc %)))

(fn [n]
  (reduce * (take n (iterate inc 1))))
