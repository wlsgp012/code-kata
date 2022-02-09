(ns dojo.problems-in-4clojure.034-implement-range)
;; https://4clojure.oxal.org/#/problem/34

(defn answer
  [s e]
  (loop [pos s r []]
    (if (= e pos)
      r
      (recur (inc pos) (conj r pos)))))

(= (answer 1 4) '(1 2 3))
(= (answer -2 2) '(-2 -1 0 1))
(= (answer 5 8) '(5 6 7))

;; others

(fn [from to]
  (take-while (complement #(= % to)) (iterate inc from)))

(fn r [x y]
  (when (< x y)
    (cons x (r (inc x) y))))
